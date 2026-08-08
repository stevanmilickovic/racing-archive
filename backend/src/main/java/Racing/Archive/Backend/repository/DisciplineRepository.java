package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
