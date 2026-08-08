package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
