package Racing.Archive.Backend;

import liquibase.integration.spring.SpringLiquibase;
import java.net.URI;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.context.WebServerApplicationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;
import static org.assertj.core.api.Assertions.*;

/** Opt-in replay against an empty disposable MySQL schema, never the application database. */
@EnabledIfEnvironmentVariable(named = "RACING_ARCHIVE_TEST_MYSQL_URL", matches = "jdbc:mysql:.*")
class MySqlArchiveMigrationTest {
    private static final String MASTER = "classpath:db/changelog/db.changelog-master.yaml";
    private static final Path EVENT_DIRECTORY = Path.of("..", "archive", "events", "2026", "2026-05-02-kotor-trojica");
    private final JsonMapper json = JsonMapper.builder().build();

    @Test
    void cleanReplayPublishesReviewedEventAndExactOriginalDownloads() throws Exception {
        DriverManagerDataSource dataSource = emptySchema("RACING_ARCHIVE_TEST_MYSQL_URL");
        migrate(dataSource, MASTER);
        verifyEvent(dataSource, 135);
        verifyGoc(dataSource);
        migrate(dataSource, MASTER);
        assertThat(new JdbcTemplate(dataSource).queryForObject("SELECT COUNT(*) FROM DATABASECHANGELOG", Long.class)).isEqualTo(23);
        verifyPublicApi(dataSource);
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "RACING_ARCHIVE_TEST_MYSQL_UPGRADE_URL", matches = "jdbc:mysql:.*")
    void upgradePreservesLegacyPeopleAndUsesKeysRatherThanLocalIds() throws Exception {
        DriverManagerDataSource dataSource = emptySchema("RACING_ARCHIVE_TEST_MYSQL_UPGRADE_URL");
        migrate(dataSource, "classpath:pre-goc-changelog.yaml");
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM clubs", Long.class)).isEqualTo(12);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM competition_classes", Long.class)).isEqualTo(16);
        var preservedResults = jdbc.queryForList("SELECT * FROM session_results ORDER BY id");
        var preservedClassifications = jdbc.queryForList("SELECT * FROM classification_results ORDER BY id");
        jdbc.update("INSERT INTO drivers (first_name,last_name) VALUES ('Preserved','Legacy')");
        for (String table : List.of("federations", "disciplines", "championships", "seasons", "drivers", "clubs",
                "vehicles", "tracks", "competition_classes", "championship_rounds", "race_sessions", "entries",
                "classifications", "source_documents")) {
            jdbc.execute("ALTER TABLE " + table + " AUTO_INCREMENT=10000");
        }
        migrate(dataSource, MASTER);
        verifyEvent(dataSource, 136);
        verifyGoc(dataSource);
        assertThat(jdbc.queryForList("SELECT * FROM session_results WHERE id IN (" +
                preservedResults.stream().map(row -> row.get("id").toString()).collect(java.util.stream.Collectors.joining(",")) + ") ORDER BY id")).isEqualTo(preservedResults);
        assertThat(jdbc.queryForList("SELECT * FROM classification_results WHERE id IN (" +
                preservedClassifications.stream().map(row -> row.get("id").toString()).collect(java.util.stream.Collectors.joining(",")) + ") ORDER BY id")).isEqualTo(preservedClassifications);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM drivers WHERE first_name='Preserved' AND last_name='Legacy' AND archive_key IS NULL", Long.class)).isEqualTo(1);
        assertThat(jdbc.queryForObject("SELECT MIN(e.id) FROM entries e JOIN championship_rounds r ON r.id=e.round_id WHERE r.archive_key='2026-06-06-goc'", Long.class)).isGreaterThanOrEqualTo(10000);
        migrate(dataSource, MASTER);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM entries", Long.class)).isEqualTo(114);
    }

    private DriverManagerDataSource emptySchema(String variable) {
        String url = System.getenv(variable);
        assertThat(url).matches("jdbc:mysql://[^/]+/racing_archive_verify_[a-zA-Z0-9_]+(?:\\?.*)?");
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url,
                System.getenv("RACING_ARCHIVE_TEST_MYSQL_USERNAME"),
                System.getenv("RACING_ARCHIVE_TEST_MYSQL_PASSWORD"));
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        String schema = jdbc.queryForObject("SELECT DATABASE()", String.class);
        assertThat(schema).matches("racing_archive_verify_[a-zA-Z0-9_]+");
        assertThat(jdbc.queryForObject(
                "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE()", Long.class)).isZero();
        return dataSource;
    }

    private void migrate(DriverManagerDataSource dataSource, String changelog) throws Exception {
        SpringLiquibase migrations = new SpringLiquibase();
        migrations.setDataSource(dataSource);
        migrations.setChangeLog(changelog);
        migrations.afterPropertiesSet();
    }

    private void verifyEvent(DriverManagerDataSource dataSource, int driverCount) throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM drivers", Long.class)).isEqualTo(driverCount);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM clubs", Long.class)).isEqualTo(13);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vehicles", Long.class)).isEqualTo(27);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM competition_classes", Long.class)).isEqualTo(16);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM entries", Long.class)).isEqualTo(114);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM drivers d WHERE d.archive_key IS NOT NULL AND NOT EXISTS (SELECT 1 FROM entries e WHERE e.driver_id=d.id)", Long.class)).isEqualTo(64);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM source_documents", Long.class)).isEqualTo(13);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM classifications", Long.class)).isEqualTo(35);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM classification_results", Long.class)).isEqualTo(228);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM round_club_results", Long.class)).isEqualTo(12);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM race_sessions", Long.class)).isEqualTo(9);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM session_results", Long.class)).isEqualTo(381);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM session_results sr JOIN race_sessions s ON s.id=sr.session_id JOIN championship_rounds r ON r.id=s.round_id WHERE s.type='PRACTICE' AND r.archive_key='2026-05-02-kotor-trojica'", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM vehicles WHERE archive_key='vehicle-wv-polo'", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM identity_aliases a JOIN vehicles v ON v.id=a.vehicle_id WHERE a.alias='WV POLO' AND v.archive_key='vehicle-vw-polo'", Long.class)).isGreaterThan(0);
        assertThat(jdbc.queryForObject("""
                SELECT ss.name FROM seasons s
                JOIN championships c ON c.id=s.championship_id
                JOIN federations f ON f.id=c.federation_id
                JOIN disciplines d ON d.id=c.discipline_id
                JOIN scoring_systems ss ON ss.id=s.scoring_system_id
                WHERE s.season_year=2026 AND c.name='Šampionat Crne Gore'
                  AND f.name='Auto i karting savez Crne Gore' AND f.country_code='ME'
                  AND d.code='HILLCLIMB'
                """, String.class)).isEqualTo("Modern hillclimb scoring system");
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM scoring_systems", Long.class)).isEqualTo(1);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM scoring_system_positions", Long.class)).isEqualTo(10);
        JsonNode evidence = json.readTree(Files.readString(EVENT_DIRECTORY.resolve("reconciliation.json")));
        for (JsonNode row : evidence.get("categoryResults")) {
            Long entry = jdbc.queryForObject("SELECT e.id FROM entries e JOIN drivers d ON d.id=e.driver_id JOIN championship_rounds r ON r.id=e.round_id WHERE d.archive_key=? AND r.archive_key='2026-05-02-kotor-trojica'", Long.class, row.get("canonicalDriverKey").asString());
            String originalVehicle = row.get("canonicalVehicleKey").asString();
            assertThat(jdbc.queryForObject("SELECT v.archive_key FROM entries e JOIN vehicles v ON v.id=e.vehicle_id WHERE e.id=?", String.class, entry)).isEqualTo(originalVehicle.equals("vehicle-wv-polo") ? "vehicle-vw-polo" : originalVehicle);
            assertThat(jdbc.queryForObject("SELECT race_number FROM entries WHERE id=?", Integer.class, entry)).isEqualTo(Integer.parseInt(row.get("number").asString()));
            for (int run = 1; run <= 2; run++) {
                var actual = jdbc.queryForMap("SELECT sr.elapsed_time_millis,sr.status,sr.position FROM session_results sr JOIN race_sessions s ON s.id=sr.session_id WHERE sr.entry_id=? AND s.session_order=?", entry, run + 1);
                assertThat(actual.get("elapsed_time_millis")).isEqualTo(nullableLong(row.get("run" + run + "Millis")));
                assertThat(actual.get("status")).isEqualTo(row.get("run" + run + "Status").asString());
                assertThat(actual.get("position")).isNull();
            }
            var result = jdbc.queryForMap("SELECT cr.elapsed_time_millis,cr.position,cr.status,cr.points FROM classification_results cr JOIN classifications c ON c.id=cr.classification_id WHERE cr.entry_id=? AND c.competition_class_id IS NOT NULL", entry);
            assertThat(result.get("elapsed_time_millis")).isEqualTo(nullableLong(row.get("totalMillis")));
            assertThat(result.get("position")).isEqualTo(row.get("classPosition").isNull() ? null : row.get("classPosition").asInt());
            assertThat(result.get("status")).isEqualTo(row.get("combinedStatus").asString());
            assertThat((java.math.BigDecimal) result.get("points")).isEqualByComparingTo(row.get("classPoints").asString().replace(',', '.'));
        }
    }

    private Long nullableLong(JsonNode value) { return value.isNull() ? null : value.asLong(); }

    private void verifyGoc(DriverManagerDataSource dataSource) throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        JsonNode evidence = json.readTree(Files.readString(Path.of("..", "archive", "events", "2026", "2026-06-06-goc", "reconciliation.json")));
        for (JsonNode person : evidence.get("drivers")) {
            var actual = jdbc.queryForMap("""
                    SELECT e.id, d.first_name, d.last_name, club.archive_key AS club_key,
                           v.archive_key AS vehicle_key, cc.archive_key AS class_key
                    FROM entries e JOIN drivers d ON d.id=e.driver_id
                    JOIN championship_rounds r ON r.id=e.round_id
                    LEFT JOIN clubs club ON club.id=e.club_id LEFT JOIN vehicles v ON v.id=e.vehicle_id
                    LEFT JOIN competition_classes cc ON cc.id=e.competition_class_id
                    WHERE r.archive_key='2026-06-06-goc' AND d.archive_key=?
                    """, person.get("canonicalDriverKey").asString());
            assertThat(actual.get("club_key")).isEqualTo(person.get("canonicalClubKey").asString());
            assertThat(actual.get("vehicle_key")).isEqualTo(person.get("canonicalVehicleKey").isNull() ? null : person.get("canonicalVehicleKey").asString());
            assertThat(actual.get("class_key")).isEqualTo(person.get("canonicalClassKey").asString());
            assertThat(actual.get("first_name") + " " + actual.get("last_name")).isEqualTo(person.get("canonicalName").asString());
        }
        for (JsonNode row : evidence.get("sourceRows")) {
            if (!row.get("recordKind").asString().equals("session-result")) continue;
            var actual = jdbc.queryForMap("""
                    SELECT sr.elapsed_time_millis,sr.position,sr.status FROM session_results sr
                    JOIN race_sessions s ON s.id=sr.session_id JOIN entries e ON e.id=sr.entry_id
                    JOIN championship_rounds r ON r.id=s.round_id JOIN drivers d ON d.id=e.driver_id
                    WHERE r.archive_key='2026-06-06-goc' AND d.archive_key=? AND s.session_order=?
                    """, row.get("canonicalDriverKey").asString(), sessionOrder(row.get("sesija").asString()));
            assertThat(actual.get("elapsed_time_millis")).isEqualTo(nullableLong(row.get("canonicalElapsedTimeMillis")));
            assertThat(actual.get("position")).isEqualTo(row.get("canonicalPosition").isNull() ? null : row.get("canonicalPosition").asInt());
            assertThat(actual.get("status")).isEqualTo(row.get("canonicalStatus").asString());
        }
        for (JsonNode classification : evidence.get("expectedClassifications")) {
            for (JsonNode row : classification.get("rows")) {
                var actual = jdbc.queryForMap("""
                        SELECT cr.elapsed_time_millis,cr.position,cr.points,cr.status FROM classification_results cr
                        JOIN classifications c ON c.id=cr.classification_id JOIN entries e ON e.id=cr.entry_id
                        JOIN drivers d ON d.id=e.driver_id JOIN championship_rounds r ON r.id=c.round_id
                        WHERE r.archive_key='2026-06-06-goc' AND c.name=? AND d.archive_key=?
                        """, classification.get("name").asString(), row.get("driverKey").asString());
                assertThat(actual.get("elapsed_time_millis")).isEqualTo(nullableLong(row.get("elapsedTimeMillis")));
                assertThat(actual.get("position")).isEqualTo(row.get("position").isNull() ? null : row.get("position").asInt());
                assertThat(actual.get("status")).isEqualTo(row.get("status").asString());
                assertThat(actual.get("points")).isNull();
            }
        }
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM session_results sr JOIN race_sessions s ON s.id=sr.session_id JOIN entries e ON e.id=sr.entry_id JOIN drivers d ON d.id=e.driver_id JOIN championship_rounds r ON r.id=e.round_id WHERE r.archive_key='2026-06-06-goc' AND d.archive_key='driver-ena-plakalo' AND s.session_order IN (1,2)", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM classification_results cr JOIN entries e ON e.id=cr.entry_id JOIN drivers d ON d.id=e.driver_id JOIN championship_rounds r ON r.id=e.round_id WHERE r.archive_key='2026-06-06-goc' AND d.archive_key='driver-ena-plakalo' AND cr.elapsed_time_millis=241807", Long.class)).isEqualTo(2);
    }

    private int sessionOrder(String key) {
        return List.of("placeni_trening_1", "placeni_trening_2", "trening_1", "trening_2", "trka_1", "trka_2").indexOf(key) + 1;
    }

    private void verifyPublicApi(DriverManagerDataSource dataSource) throws Exception {
        try (var context = new SpringApplicationBuilder(BackendApplication.class).run(
                "--server.port=0", "--spring.datasource.url=" + dataSource.getUrl(),
                "--spring.datasource.username=" + dataSource.getUsername(),
                "--spring.datasource.password=" + dataSource.getPassword(),
                "--racing-archive.sources-directory=" + Path.of("..", "archive", "events").toAbsolutePath())) {
            int port = ((WebServerApplicationContext) context).getWebServer().getPort();
            String base = "http://localhost:" + port + "/public";
            HttpClient http = HttpClient.newHttpClient();
            JsonNode dashboard = getJson(http, base + "/dashboard");
            assertThat(dashboard.get(0).get("scoringKnown").asBoolean()).isTrue();
            JdbcTemplate jdbc = new JdbcTemplate(dataSource);
            int standingCount = 0;
            for (JsonNode group : dashboard.get(0).get("standings")) {
                for (JsonNode driver : group.get("drivers")) {
                    standingCount++;
                    var classResults = jdbc.queryForList("""
                            SELECT cr.points, cr.status, cr.position, p.points AS scheduled_points,
                                   r.archive_key, host.country_code AS host_country
                            FROM classification_results cr
                            JOIN classifications c ON c.id=cr.classification_id
                            JOIN competition_classes cc ON cc.id=c.competition_class_id
                            JOIN entries e ON e.id=cr.entry_id
                            JOIN championship_rounds r ON r.id=c.round_id
                            LEFT JOIN federations host ON host.id=r.host_federation_id
                            LEFT JOIN seasons s ON s.id=?
                            LEFT JOIN scoring_system_positions p ON p.scoring_system_id=s.scoring_system_id
                                AND p.position=cr.position
                            WHERE e.driver_id=? AND cc.code=? AND c.status='FINALIZED'
                            """, dashboard.get(0).get("id").asLong(), driver.get("driverId").asLong(), group.get("classCode").asString());
                    BigDecimal raw = BigDecimal.ZERO;
                    BigDecimal lowestEligible = null;
                    boolean calculated = false;
                    for (var result : classResults) {
                        BigDecimal points = (BigDecimal) result.get("points");
                        if (points == null) {
                            // This fixture's missing awards are exclusively Goč, a foreign-host
                            // round with full points under the approved CG season rules.
                            assertThat(result.get("archive_key")).isEqualTo("2026-06-06-goc");
                            assertThat(result.get("host_country")).isEqualTo("RS");
                            calculated = true;
                            points = BigDecimal.ZERO;
                            if (result.get("status").equals("FINISHED")) {
                                assertThat(result.get("position")).isNotNull();
                                if (result.get("scheduled_points") != null) points = (BigDecimal) result.get("scheduled_points");
                            }
                        }
                        raw = raw.add(points);
                        if (!result.get("status").equals("DSQ")) {
                            lowestEligible = lowestEligible == null ? points : lowestEligible.min(points);
                        }
                    }
                    BigDecimal adjusted = classResults.size() > 1 && lowestEligible != null ? raw.subtract(lowestEligible) : raw;
                    assertThat(new BigDecimal(driver.get("rawPoints").toString())).isEqualByComparingTo(raw);
                    assertThat(new BigDecimal(driver.get("adjustedPoints").toString())).isEqualByComparingTo(adjusted);
                    assertThat(driver.get("hasCalculatedPoints").asBoolean()).isEqualTo(calculated);
                }
            }
            assertThat(standingCount).isEqualTo(71);
            assertThat(dashboard.get(0).get("completedRounds").size()).isEqualTo(2);
            for (JsonNode publishedRound : dashboard.get(0).get("completedRounds")) {
                long round = publishedRound.get("id").asLong();
                boolean goc = publishedRound.get("roundNumber").asInt() == 2;
                JsonNode sessions = getJson(http, base + "/rounds/" + round + "/sessions");
                assertThat(sessions.size()).isEqualTo(goc ? 6 : 3);
                for (JsonNode session : sessions) {
                    JsonNode results = getJson(http, base + "/sessions/" + session.get("id").asLong() + "/results");
                    int order = session.get("sessionOrder").asInt();
                    int expectedCount = goc ? (order == 1 ? 33 : order == 2 ? 28 : 46) : session.get("type").asString().equals("PRACTICE") ? 0 : 68;
                    assertThat(results.size()).isEqualTo(expectedCount);
                }
                JsonNode classifications = getJson(http, base + "/rounds/" + round + "/classifications");
                assertThat(classifications.size()).isEqualTo(goc ? 17 : 18);
                for (JsonNode classification : classifications) {
                    JsonNode detail = getJson(http, base + "/classifications/" + classification.get("id").asLong());
                    assertThat(detail.get("results").isEmpty()).isFalse();
                }
                JsonNode clubs = getJson(http, base + "/rounds/" + round + "/club-results");
                if (goc) assertThat(clubs.isEmpty()).isTrue();
                else assertThat(clubs.get(0).get("points").asInt()).isEqualTo(75);
                JsonNode documents = getJson(http, base + "/rounds/" + round + "/sources");
                assertThat(documents.size()).isEqualTo(goc ? 10 : 3);
                for (JsonNode document : documents) {
                    var response = http.send(HttpRequest.newBuilder(URI.create("http://localhost:" + port + document.get("downloadUrl").asString())).build(), HttpResponse.BodyHandlers.ofByteArray());
                    assertThat(response.statusCode()).isEqualTo(200);
                    assertThat(response.headers().firstValue("Content-Type")).hasValue(document.get("mediaType").asString());
                    assertThat(response.headers().firstValue("Content-Disposition").orElseThrow()).contains("attachment");
                    assertThat(HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(response.body()))).isEqualTo(document.get("sha256").asString());
                }
            }
            Long profileOnly = new JdbcTemplate(dataSource).queryForObject("SELECT id FROM drivers WHERE archive_key='driver-perica-mardonovic'", Long.class);
            assertThat(getJson(http, base + "/drivers/" + profileOnly + "/results").get("results").isEmpty()).isTrue();
        }
    }

    private JsonNode getJson(HttpClient http, String url) throws Exception {
        var response = http.send(HttpRequest.newBuilder(URI.create(url)).build(), HttpResponse.BodyHandlers.ofString());
        assertThat(response.statusCode()).as(url).isEqualTo(200);
        return json.readTree(response.body());
    }
}
