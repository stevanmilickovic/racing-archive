package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.CompetitionClass;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionClassRepository extends JpaRepository<CompetitionClass, Long> {

    Optional<CompetitionClass> findBySeasonIdAndCodeIgnoreCase(Long seasonId, String code);
}
