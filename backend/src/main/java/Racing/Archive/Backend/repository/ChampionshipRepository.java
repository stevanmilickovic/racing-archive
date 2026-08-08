package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {
}
