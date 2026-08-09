package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.SessionResult;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionResultRepository extends JpaRepository<SessionResult, Long> {

    Optional<SessionResult> findBySessionIdAndEntryId(Long sessionId, Long entryId);
    List<SessionResult> findBySessionIdOrderByPositionAsc(Long sessionId);
}
