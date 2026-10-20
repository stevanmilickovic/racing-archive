package Racing.Archive.Backend.publicapi;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Read-only identity inventory for reviewing source documents against the running archive. */
@RestController
@RequestMapping("/public/catalog")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublicCatalogController {
    private final JdbcTemplate jdbc;

    @GetMapping
    PublicCatalogResponse catalog() {
        List<CatalogIdentity> identities = jdbc.query("""
                SELECT 'driver' AS kind, id, archive_key, CONCAT(first_name, ' ', last_name) AS name,
                       nationality_code AS detail, identity_notes FROM drivers
                UNION ALL
                SELECT 'club', id, archive_key, name, code, identity_notes FROM clubs
                UNION ALL
                SELECT 'vehicle', id, archive_key, COALESCE(display_name, CONCAT(manufacturer, ' ', COALESCE(model, ''))),
                       manufacturer, identity_notes FROM vehicles
                UNION ALL
                SELECT 'track', id, archive_key, name, location, identity_notes FROM tracks
                UNION ALL
                SELECT 'class', id, archive_key, code, name, identity_notes FROM competition_classes
                ORDER BY kind, id
                """, (row, index) -> new CatalogIdentity(row.getString("kind"), row.getLong("id"),
                row.getString("archive_key"), row.getString("name"), row.getString("detail"),
                row.getString("identity_notes")));
        List<CatalogAlias> aliases = jdbc.query("""
                SELECT alias, driver_id, club_id, vehicle_id, track_id, competition_class_id,
                       source_document_id, source_locator, notes FROM identity_aliases ORDER BY id
                """, (row, index) -> new CatalogAlias(row.getString("alias"),
                row.getObject("driver_id", Long.class), row.getObject("club_id", Long.class),
                row.getObject("vehicle_id", Long.class), row.getObject("track_id", Long.class),
                row.getObject("competition_class_id", Long.class), row.getObject("source_document_id", Long.class),
                row.getString("source_locator"), row.getString("notes")));
        return new PublicCatalogResponse(identities, aliases);
    }
}

record PublicCatalogResponse(List<CatalogIdentity> identities, List<CatalogAlias> aliases) {}
record CatalogIdentity(String kind, Long id, String archiveKey, String name, String detail, String notes) {}
record CatalogAlias(String alias, Long driverId, Long clubId, Long vehicleId, Long trackId,
                    Long competitionClassId, Long sourceDocumentId, String sourceLocator, String notes) {}
