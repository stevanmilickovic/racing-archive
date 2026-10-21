package Racing.Archive.Backend;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;
import java.util.UUID;
import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.context.WebServerApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;
import static org.assertj.core.api.Assertions.*;

/** Isolated review-fixture replay; full MySQL replay is also exercised by the opt-in test. */
class KuciArchiveMigrationTest {
    private static final String BASELINE = "classpath:kuci-h2-baseline.yaml";
    private static final String CURRENT = "classpath:kuci-h2-changelog.yaml";
    private static final String EVENT = "classpath:db/changelog/019-kuci-2026.yaml";
    private static final Path DIRECTORY = Path.of("..", "archive", "events", "2026", "2026-06-20-kuci");
    private static final JsonMapper JSON = JsonMapper.builder().build();

    @Test
    void cleanReplayPreservesReviewedRowsAndServesOriginalDownloads() throws Exception {
        var source = isolated();
        migrate(source, CURRENT);
        verifyArchive(source);
        migrate(source, CURRENT);
        assertThat(new JdbcTemplate(source).queryForObject("SELECT COUNT(*) FROM DATABASECHANGELOG", Integer.class)).isEqualTo(25);
        verifyPublicApi(source, CURRENT);
    }

    @Test
    void upgradePreservesHistoryAndLegacyRowsWithShiftedGeneratedIds() throws Exception {
        var source = isolated();
        migrate(source, BASELINE);
        JdbcTemplate jdbc = new JdbcTemplate(source);
        var entries = jdbc.queryForList("SELECT * FROM entries ORDER BY id");
        var results = jdbc.queryForList("SELECT * FROM session_results ORDER BY id");
        var classifications = jdbc.queryForList("SELECT * FROM classification_results ORDER BY id");
        var aliases = jdbc.queryForList("SELECT * FROM identity_aliases ORDER BY id");
        jdbc.update("INSERT INTO drivers(first_name,last_name) VALUES ('Unreviewed','Legacy')");
        for (String table : List.of("drivers", "clubs", "vehicles", "tracks", "championship_rounds", "entries",
                "race_sessions", "classifications", "session_results", "classification_results", "source_documents")) {
            jdbc.execute("ALTER TABLE " + table + " ALTER COLUMN id RESTART WITH 10000");
        }
        migrate(source, CURRENT);
        verifyArchive(source);
        assertThat(jdbc.queryForList("SELECT * FROM entries WHERE id<10000 ORDER BY id")).isEqualTo(entries);
        assertThat(jdbc.queryForList("SELECT * FROM session_results WHERE id<10000 ORDER BY id")).isEqualTo(results);
        assertThat(jdbc.queryForList("SELECT * FROM classification_results WHERE id<10000 ORDER BY id")).isEqualTo(classifications);
        assertThat(jdbc.queryForList("SELECT * FROM identity_aliases WHERE id<=? ORDER BY id", aliases.size())).isEqualTo(aliases);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM drivers WHERE first_name='Unreviewed' AND last_name='Legacy' AND archive_key IS NULL", Long.class)).isEqualTo(1);
        assertThat(jdbc.queryForObject("SELECT MIN(id) FROM entries WHERE round_id=(SELECT id FROM championship_rounds WHERE archive_key='2026-06-20-kuci')", Long.class)).isGreaterThanOrEqualTo(10000);
    }

    @Test
    void missingIdentityOrConflictingLegacyNameHaltsBeforeInsertingAnything() throws Exception {
        var source = isolated();
        migrate(source, BASELINE);
        JdbcTemplate jdbc = new JdbcTemplate(source);
        jdbc.update("UPDATE drivers SET archive_key='fixture-missing-milan' WHERE archive_key='driver-milan-bracanovic'");
        assertThatThrownBy(() -> migrate(source, EVENT)).hasStackTraceContaining("Precondition");
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM drivers WHERE archive_key='driver-milos-perunicic'", Long.class)).isZero();
        jdbc.update("UPDATE drivers SET archive_key='driver-milan-bracanovic' WHERE archive_key='fixture-missing-milan'");
        jdbc.update("INSERT INTO drivers(first_name,last_name) VALUES ('MILOŠ','PERUNIČIĆ')");
        assertThatThrownBy(() -> migrate(source, EVENT)).hasStackTraceContaining("Precondition");
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM drivers WHERE archive_key='driver-milos-perunicic'", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM championship_rounds WHERE archive_key='2026-06-20-kuci'", Long.class)).isZero();
    }

    private DriverManagerDataSource isolated() {
        return new DriverManagerDataSource("jdbc:h2:mem:kuci_" + UUID.randomUUID()
                + ";MODE=MySQL;DB_CLOSE_DELAY=-1", "sa", "");
    }

    static void migrate(DriverManagerDataSource source, String changelog) throws Exception {
        var migrations = new SpringLiquibase();
        migrations.setDataSource(source);
        migrations.setChangeLog(changelog);
        migrations.afterPropertiesSet();
    }

    static void verifyArchive(DriverManagerDataSource source) throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(source);
        JsonNode evidence = JSON.readTree(Files.readString(DIRECTORY.resolve("reconciliation.json")));
        Long round = jdbc.queryForObject("SELECT id FROM championship_rounds WHERE archive_key='2026-06-20-kuci'", Long.class);
        var event = jdbc.queryForMap("SELECT round_number,starts_on,ends_on,status FROM championship_rounds WHERE id=?", round);
        assertThat(event.get("round_number")).isEqualTo(3);
        assertThat(event.get("starts_on").toString()).isEqualTo("2026-06-20");
        assertThat(event.get("ends_on").toString()).isEqualTo("2026-06-21");
        assertThat(event.get("status")).isEqualTo("COMPLETED");
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM round_season_participations WHERE round_id=?", Long.class, round)).isEqualTo(1);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM entries WHERE round_id=?", Long.class, round)).isEqualTo(62);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM race_sessions WHERE round_id=?", Long.class, round)).isEqualTo(4);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM classifications WHERE round_id=? AND status='FINALIZED'", Long.class, round)).isEqualTo(17);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM classifications WHERE round_id=? AND competition_class_id IS NULL", Long.class, round)).isEqualTo(1);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM drivers WHERE archive_key='driver-milos-bracanovic'", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vehicles WHERE archive_key IN ('vehicle-renault-megane','vehicle-mini-cooper')", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vehicles WHERE archive_key IN ('vehicle-peugeot','vehicle-suzuki','vehicle-honda','vehicle-skoda','vehicle-opel','vehicle-ford','vehicle-seat','vehicle-norma') AND model IS NULL", Long.class)).isEqualTo(8);
        assertThat(jdbc.queryForObject("SELECT display_name FROM vehicles WHERE archive_key='vehicle-vw-golf'", String.class)).isEqualTo("VW GOLF");
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM clubs WHERE archive_key='club-mugosa' AND country_code IS NULL AND federation_id IS NULL", Long.class)).isEqualTo(1);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM entries e JOIN drivers d ON d.id=e.driver_id WHERE e.round_id=? AND d.archive_key='driver-milos-milosev'", Long.class, round)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM drivers WHERE archive_key='driver-milos-milosev' AND nationality_code IS NULL", Long.class)).isEqualTo(1);

        for (JsonNode person : evidence.get("participants")) {
            var actual = jdbc.queryForMap("""
                    SELECT d.first_name,d.last_name,e.race_number,club.archive_key AS club_key,
                           v.archive_key AS vehicle_key,cc.archive_key AS class_key
                    FROM entries e JOIN drivers d ON d.id=e.driver_id
                    LEFT JOIN clubs club ON club.id=e.club_id LEFT JOIN vehicles v ON v.id=e.vehicle_id
                    LEFT JOIN competition_classes cc ON cc.id=e.competition_class_id
                    WHERE e.round_id=? AND d.archive_key=?
                    """, round, person.get("canonicalDriverKey").asString());
            assertThat(actual.get("first_name") + " " + actual.get("last_name")).isEqualTo(person.get("canonicalName").asString());
            assertThat(actual.get("race_number")).isEqualTo(person.get("canonicalRaceNumber").asInt());
            assertThat(actual.get("club_key")).isEqualTo(nullableString(person.get("canonicalClubKey")));
            assertThat(actual.get("vehicle_key")).isEqualTo(nullableString(person.get("canonicalVehicleKey")));
            assertThat(actual.get("class_key")).isEqualTo(person.get("canonicalClassKey").asString());
        }
        for (JsonNode row : evidence.get("proposedSessionResults")) {
            var actual = jdbc.queryForMap("""
                    SELECT sr.elapsed_time_millis,sr.position,sr.status
                    FROM session_results sr JOIN race_sessions s ON s.id=sr.session_id
                    JOIN entries e ON e.id=sr.entry_id JOIN drivers d ON d.id=e.driver_id
                    WHERE s.round_id=? AND s.session_order=? AND d.archive_key=?
                    """, round, sessionOrder(row.get("session").asString()), row.get("canonicalDriverKey").asString());
            assertThat(actual.get("elapsed_time_millis")).isEqualTo(nullableLong(row.get("elapsedTimeMillis")));
            assertThat(actual.get("status")).isEqualTo(row.get("status").asString());
            assertThat(actual.get("position")).isNull();
        }
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM session_results sr JOIN race_sessions s ON s.id=sr.session_id WHERE s.round_id=?", Long.class, round)).isEqualTo(207);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM session_results sr JOIN race_sessions s ON s.id=sr.session_id WHERE s.round_id=? AND s.type='RACE' AND sr.status<>'FINISHED'", Long.class, round)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM session_results sr JOIN race_sessions s ON s.id=sr.session_id JOIN entries e ON e.id=sr.entry_id JOIN drivers d ON d.id=e.driver_id WHERE s.round_id=? AND s.session_order=2 AND d.archive_key='driver-ena-plakalo'", Long.class, round)).isZero();
        for (JsonNode group : evidence.get("expectedClassifications")) {
            for (JsonNode row : group.get("rows")) {
                var actual = jdbc.queryForMap("""
                        SELECT cr.position,cr.elapsed_time_millis,cr.points,cr.status
                        FROM classification_results cr JOIN classifications c ON c.id=cr.classification_id
                        JOIN entries e ON e.id=cr.entry_id JOIN drivers d ON d.id=e.driver_id
                        WHERE c.round_id=? AND c.name=? AND d.archive_key=?
                        """, round, group.get("name").asString(), row.get("driverKey").asString());
                assertThat(actual.get("position")).isEqualTo(row.get("position").asInt());
                assertThat(actual.get("elapsed_time_millis")).isEqualTo(row.get("elapsedTimeMillis").asLong());
                assertThat(actual.get("points")).isNull();
                assertThat(actual.get("status")).isEqualTo("FINISHED");
            }
        }
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM classification_results cr JOIN classifications c ON c.id=cr.classification_id WHERE c.round_id=?", Long.class, round)).isEqualTo(98);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM source_documents WHERE round_id=?", Long.class, round)).isEqualTo(5);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM identity_aliases a JOIN drivers d ON d.id=a.driver_id WHERE d.archive_key='driver-milan-bracanovic' AND a.alias='BRACANOVIĆ Miloš' AND a.source_locator LIKE '%752%'", Long.class)).isEqualTo(2);
    }

    static void verifyPublicApi(DriverManagerDataSource source, String changelog) throws Exception {
        try (var context = new SpringApplicationBuilder(BackendApplication.class).run(
                "--server.port=0", "--spring.datasource.url=" + source.getUrl(),
                "--spring.datasource.username=" + source.getUsername(), "--spring.datasource.password=" + source.getPassword(),
                "--spring.jpa.hibernate.ddl-auto=none", "--spring.liquibase.change-log=" + changelog,
                "--racing-archive.sources-directory=" + Path.of("..", "archive", "events").toAbsolutePath())) {
            int port = ((WebServerApplicationContext) context).getWebServer().getPort();
            String base = "http://localhost:" + port + "/public";
            HttpClient http = HttpClient.newHttpClient();
            JdbcTemplate jdbc = new JdbcTemplate(source);
            Long round = jdbc.queryForObject("SELECT id FROM championship_rounds WHERE archive_key='2026-06-20-kuci'", Long.class);
            JsonNode detail = getJson(http, base + "/rounds/" + round);
            assertThat(detail.get("startsOn").asString()).isEqualTo("2026-06-20");
            assertThat(detail.get("endsOn").asString()).isEqualTo("2026-06-21");
            assertThat(detail.get("trackName").asString()).isEqualTo("Kuči");
            var dashboard = getJson(http, base + "/dashboard").get(0);
            assertThat(dashboard.get("completedRounds").size()).isEqualTo(3);
            assertThat(dashboard.get("scoringKnown").asBoolean()).isTrue();
            boolean sawReducedPoints = false;
            for (JsonNode group : dashboard.get("standings")) {
                for (JsonNode person : group.get("drivers")) {
                    String name = person.get("driverName").asString();
                    if (name.equals("RADOMIR SAVIĆEVIĆ")) {
                        assertThat(group.get("classCode").asString()).isEqualTo("H 1400");
                        assertThat(person.get("hasCalculatedPoints").asBoolean()).isTrue();
                        // Two classified H 1400 finishers: domestic host gives half of 25 points.
                        assertThat(person.get("rawPoints").asDouble()).isEqualTo(12.5);
                        sawReducedPoints = true;
                    }
                    if (name.equals("MILAN BRACANOVIĆ")) assertThat(group.get("classCode").asString()).isEqualTo("H 1400");
                }
            }
            assertThat(sawReducedPoints).isTrue();
            for (JsonNode s : getJson(http, base + "/rounds/" + round + "/sessions")) {
                int order = s.get("sessionOrder").asInt();
                var results = getJson(http, base + "/sessions/" + s.get("id").asLong() + "/results");
                assertThat(results.size()).isEqualTo(order == 1 ? 62 : order == 2 ? 47 : 49);
                for (JsonNode row : results) {
                    assertThat(row.get("position").isNull()).isTrue();
                    if (row.get("driverName").asString().equals("MILAN BRACANOVIĆ")) {
                        assertThat(order).isLessThanOrEqualTo(2);
                        if (order == 2) assertThat(row.get("elapsedTimeMillis").asLong()).isEqualTo(155732);
                    }
                }
            }
            var classifications = getJson(http, base + "/rounds/" + round + "/classifications");
            assertThat(classifications.size()).isEqualTo(17);
            for (JsonNode c : classifications) {
                var rows = getJson(http, base + "/classifications/" + c.get("id").asLong()).get("results");
                assertThat(rows.isEmpty()).isFalse();
                if (c.get("classCode").isNull()) {
                    assertThat(rows.size()).isEqualTo(49);
                    assertThat(rows.get(0).get("driverName").asString()).isEqualTo("ALDIN ĆOROVIĆ");
                    assertThat(rows.get(0).get("elapsedTimeMillis").asLong()).isEqualTo(248397);
                }
            }
            assertThat(getJson(http, base + "/rounds/" + round + "/club-results").isEmpty()).isTrue();
            var documents = getJson(http, base + "/rounds/" + round + "/sources");
            assertThat(documents.size()).isEqualTo(5);
            for (JsonNode document : documents) {
                var response = http.send(HttpRequest.newBuilder(URI.create("http://localhost:" + port + document.get("downloadUrl").asString())).build(), HttpResponse.BodyHandlers.ofByteArray());
                assertThat(response.statusCode()).isEqualTo(200);
                assertThat(response.headers().firstValue("Content-Type")).hasValue(document.get("mediaType").asString());
                assertThat(response.headers().firstValue("Content-Disposition").orElseThrow()).contains("attachment");
                assertThat(HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(response.body()))).isEqualTo(document.get("sha256").asString());
            }
        }
    }

    private static int sessionOrder(String key) { return List.of("practice-1", "practice-2", "race-1", "race-2").indexOf(key) + 1; }
    private static String nullableString(JsonNode value) { return value.isNull() ? null : value.asString(); }
    private static Long nullableLong(JsonNode value) { return value.isNull() ? null : value.asLong(); }
    private static JsonNode getJson(HttpClient http, String url) throws Exception {
        var response = http.send(HttpRequest.newBuilder(URI.create(url)).build(), HttpResponse.BodyHandlers.ofString());
        assertThat(response.statusCode()).as(url).isEqualTo(200);
        return JSON.readTree(response.body());
    }
}
