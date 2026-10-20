package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.SourceDocument;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceDocumentRepository extends JpaRepository<SourceDocument, Long> {
    List<SourceDocument> findByRoundIdOrderByIdAsc(Long roundId);
}
