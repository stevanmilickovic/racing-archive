package Racing.Archive.Backend.publicapi;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/** Publishes recorded classifications and points without recalculating them from a run. */
@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublicClassificationController {
    private final JdbcTemplate jdbc;

    @GetMapping("/rounds/{roundId}/classifications")
    List<PublicClassificationResponse> classifications(@PathVariable Long roundId) {
        return jdbc.query("""
                SELECT c.id, c.round_id, c.name, c.status, cc.code AS class_code
                FROM classifications c LEFT JOIN competition_classes cc ON cc.id=c.competition_class_id
                WHERE c.round_id=? ORDER BY CASE WHEN cc.id IS NULL THEN 0 ELSE 1 END, c.name
                """, (row, index) -> new PublicClassificationResponse(row.getLong("id"),
                row.getLong("round_id"), row.getString("name"), row.getString("status"),
                row.getString("class_code")), roundId);
    }

    @GetMapping("/classifications/{id}")
    PublicClassificationDetailResponse classification(@PathVariable Long id) {
        List<PublicClassificationResponse> found = jdbc.query("""
                SELECT c.id, c.round_id, c.name, c.status, cc.code AS class_code
                FROM classifications c LEFT JOIN competition_classes cc ON cc.id=c.competition_class_id
                WHERE c.id=?
                """, (row, index) -> new PublicClassificationResponse(row.getLong("id"),
                row.getLong("round_id"), row.getString("name"), row.getString("status"),
                row.getString("class_code")), id);
        if (found.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<PublicClassificationResultResponse> results = jdbc.query("""
                SELECT d.id AS driver_id, CONCAT(d.first_name, ' ', d.last_name) AS driver_name,
                       e.race_number, cc.code AS class_code, cr.position, cr.elapsed_time_millis,
                       cr.status, cr.points, club.name AS club_name, v.display_name AS vehicle_name
                FROM classification_results cr JOIN entries e ON e.id=cr.entry_id
                JOIN drivers d ON d.id=e.driver_id
                LEFT JOIN competition_classes cc ON cc.id=e.competition_class_id
                LEFT JOIN clubs club ON club.id=e.club_id LEFT JOIN vehicles v ON v.id=e.vehicle_id
                WHERE cr.classification_id=?
                ORDER BY CASE WHEN cr.position IS NULL THEN 1 ELSE 0 END, cr.position, e.race_number, d.id
                """, (row, index) -> new PublicClassificationResultResponse(row.getLong("driver_id"),
                row.getString("driver_name"), row.getObject("race_number", Integer.class),
                row.getString("class_code"), row.getObject("position", Integer.class),
                row.getObject("elapsed_time_millis", Long.class), row.getString("status"),
                row.getBigDecimal("points"), row.getString("club_name"), row.getString("vehicle_name")), id);
        return new PublicClassificationDetailResponse(found.getFirst(), results);
    }

    @GetMapping("/rounds/{roundId}/club-results")
    List<PublicClubResultResponse> clubs(@PathVariable Long roundId) {
        return jdbc.query("""
                SELECT cr.position, club.name, cr.points, cr.source_document_id, cr.source_locator
                FROM round_club_results cr JOIN clubs club ON club.id=cr.club_id
                WHERE cr.round_id=? ORDER BY cr.position, club.id
                """, (row, index) -> new PublicClubResultResponse(row.getInt("position"),
                row.getString("name"), row.getBigDecimal("points"), row.getLong("source_document_id"),
                row.getString("source_locator")), roundId);
    }
}

record PublicClassificationResponse(Long id, Long roundId, String name, String status, String classCode) {}
record PublicClassificationDetailResponse(PublicClassificationResponse classification,
                                          List<PublicClassificationResultResponse> results) {}
record PublicClassificationResultResponse(Long driverId, String driverName, Integer raceNumber,
        String classCode, Integer position, Long elapsedTimeMillis, String status, BigDecimal points,
        String clubName, String vehicleName) {}
record PublicClubResultResponse(Integer position, String clubName, BigDecimal points,
                               Long sourceDocumentId, String sourceLocator) {}
