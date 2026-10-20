package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.ClassificationResult;
import Racing.Archive.Backend.domain.PublicationStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassificationResultRepository extends JpaRepository<ClassificationResult, Long> {
    @Query("""
            SELECT result FROM ClassificationResult result
            JOIN FETCH result.classification classification
            JOIN FETCH classification.competitionClass
            JOIN FETCH result.entry entry
            JOIN FETCH entry.driver
            WHERE classification.round.id IN :roundIds
              AND classification.status = :status
            """)
    List<ClassificationResult> findClassResults(@Param("roundIds") List<Long> roundIds,
                                               @Param("status") PublicationStatus status);
}
