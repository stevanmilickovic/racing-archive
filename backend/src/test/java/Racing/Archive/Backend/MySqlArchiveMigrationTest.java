package Racing.Archive.Backend;

import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static org.assertj.core.api.Assertions.*;

/** Opt-in replay against an empty disposable MySQL schema, never the application database. */
@EnabledIfEnvironmentVariable(named = "RACING_ARCHIVE_TEST_MYSQL_URL", matches = "jdbc:mysql:.*")
class MySqlArchiveMigrationTest {
    @Test
    void fullMigrationReplayStartsWithoutSpeculativeClubsOrClassesAndRetainsScoring() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                System.getenv("RACING_ARCHIVE_TEST_MYSQL_URL"),
                System.getenv("RACING_ARCHIVE_TEST_MYSQL_USERNAME"),
                System.getenv("RACING_ARCHIVE_TEST_MYSQL_PASSWORD"));
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        String schema = jdbc.queryForObject("SELECT DATABASE()", String.class);
        assertThat(schema).matches("racing_archive_verify_[a-zA-Z0-9_]+");
        assertThat(jdbc.queryForObject(
                "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE()", Long.class)).isZero();
        SpringLiquibase migrations = new SpringLiquibase();
        migrations.setDataSource(dataSource);
        migrations.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");
        migrations.afterPropertiesSet();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM clubs", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM competition_classes", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM drivers", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM source_documents", Long.class)).isZero();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM scoring_systems", Long.class)).isEqualTo(1);
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM scoring_system_positions", Long.class)).isEqualTo(10);
        assertThat(jdbc.queryForObject("SELECT IS_NULLABLE FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='entries' AND COLUMN_NAME='competition_class_id'", String.class)).isEqualTo("YES");
        migrations.afterPropertiesSet();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM DATABASECHANGELOG", Long.class)).isEqualTo(14);
    }
}
