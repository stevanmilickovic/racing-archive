package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.RoundSeasonParticipation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundSeasonParticipationRepository extends JpaRepository<RoundSeasonParticipation, Long> {
    List<RoundSeasonParticipation> findBySeasonId(Long seasonId);
    List<RoundSeasonParticipation> findByRoundId(Long roundId);
    void deleteByRoundId(Long roundId);
}
