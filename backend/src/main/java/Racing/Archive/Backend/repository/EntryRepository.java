package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.Entry;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    Optional<Entry> findByRoundIdAndDriverId(Long roundId, Long driverId);
}
