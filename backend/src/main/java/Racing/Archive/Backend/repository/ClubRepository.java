package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.Club;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {

    Optional<Club> findFirstByNameIgnoreCase(String name);
}
