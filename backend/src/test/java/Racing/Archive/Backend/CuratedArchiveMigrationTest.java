package Racing.Archive.Backend;

import java.util.UUID;
import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import static org.assertj.core.api.Assertions.*;

/** Tests only the new upgrade against a minimal legacy fixture, never a live archive. */
class CuratedArchiveMigrationTest {
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbc;

    @BeforeEach
    void migrate() throws Exception {
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:" + UUID.randomUUID()
                + ";MODE=MySQL;DB_CLOSE_DELAY=-1", "sa", "");
        new ResourceDatabasePopulator(new ClassPathResource("legacy-archive-fixture.sql")).execute(dataSource);
        jdbc = new JdbcTemplate(dataSource);
        upgrade();
    }

    @Test
    void preservesLegacyRecordsAllowsUnknownEntryDetailsAndReappliesSafely() throws Exception {
        assertThat(jdbc.queryForObject("SELECT first_name FROM drivers WHERE id=1", String.class)).isEqualTo("Legacy");
        jdbc.update("INSERT INTO entries (id, driver_id, vehicle_id, club_id, competition_class_id) VALUES (1,1,NULL,NULL,NULL)");
        jdbc.update("UPDATE drivers SET archive_key='reviewed-legacy-driver' WHERE id=1");
        upgrade();
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM entries WHERE competition_class_id IS NULL", Long.class)).isEqualTo(1L);
        assertThat(jdbc.queryForObject("SELECT archive_key FROM drivers WHERE id=1", String.class)).isEqualTo("reviewed-legacy-driver");
    }

    @Test
    void sameNamesAndAliasesCanBelongToDifferentDriversButOwnershipIsEnforced() {
        jdbc.update("INSERT INTO drivers (id, first_name, last_name, archive_key) VALUES (2,'Legacy','Driver','another-driver')");
        jdbc.update("INSERT INTO identity_aliases (alias,driver_id) VALUES ('L. Driver',1),('L. Driver',2)");
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM identity_aliases WHERE alias='L. Driver'", Long.class)).isEqualTo(2L);
        assertThatThrownBy(() -> jdbc.update("INSERT INTO identity_aliases (alias) VALUES ('No owner')"))
                .isInstanceOf(org.springframework.dao.DataIntegrityViolationException.class);
        assertThatThrownBy(() -> jdbc.update("INSERT INTO identity_aliases (alias,driver_id,club_id) VALUES ('Two owners',1,1)"))
                .isInstanceOf(org.springframework.dao.DataIntegrityViolationException.class);
        assertThatThrownBy(() -> jdbc.update("INSERT INTO identity_aliases (alias,driver_id) VALUES ('Missing driver',999)"))
                .isInstanceOf(org.springframework.dao.DataIntegrityViolationException.class);
        assertThatThrownBy(() -> jdbc.update("INSERT INTO drivers (id,first_name,last_name,archive_key) VALUES (3,'Other','Driver','another-driver')"))
                .isInstanceOf(org.springframework.dao.DataIntegrityViolationException.class);
    }

    private void upgrade() throws Exception {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/012-curated-archive.yaml");
        liquibase.afterPropertiesSet();
    }

    @Test
    void retiresOnlyUnusedUnreviewedSeedsWithoutDeletingReferencedIdentities() throws Exception {
        jdbc.update("UPDATE clubs SET name='AMSK Kotor',code='KOTOR',country_code='ME' WHERE id=1");
        jdbc.update("INSERT INTO clubs (id,name,code,country_code,archive_key) VALUES (2,'AMSK Podi','PODI','ME','reviewed-podi'),(3,'AMSK Tivat','TIVAT','ME',NULL)");
        jdbc.update("INSERT INTO competition_classes (id,code,archive_key) VALUES (1,'H 1000',NULL),(2,'N 1150','reviewed-class'),(3,'A 1150',NULL),(4,'H 1150',NULL),(5,'N 1400',NULL)");
        jdbc.update("INSERT INTO entries (id,driver_id,club_id,competition_class_id) VALUES (1,1,3,3)");
        jdbc.update("INSERT INTO points_schemes (id,competition_class_id) VALUES (1,4)");
        jdbc.update("INSERT INTO identity_aliases (alias,competition_class_id) VALUES ('Confirmed alias',5)");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/013-retire-unreviewed-reference-seeds.yaml");
        liquibase.afterPropertiesSet();
        assertThat(jdbc.queryForList("SELECT id FROM clubs ORDER BY id", Long.class)).containsExactly(2L,3L);
        assertThat(jdbc.queryForList("SELECT id FROM competition_classes ORDER BY id", Long.class)).containsExactly(2L,3L,4L,5L);
    }
}
