package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.RaceSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceSessionRepository extends JpaRepository<RaceSession, Long> {
}
