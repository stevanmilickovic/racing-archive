package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.ScoringSystemPosition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoringSystemPositionRepository extends JpaRepository<ScoringSystemPosition, Long> {
    List<ScoringSystemPosition> findByScoringSystemId(Long scoringSystemId);
}
