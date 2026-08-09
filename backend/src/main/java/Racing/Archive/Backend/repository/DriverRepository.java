package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.Driver;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}
