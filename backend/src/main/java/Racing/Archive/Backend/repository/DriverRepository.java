package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.Driver;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findFirstByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndNationalityCode(String firstName, String lastName, String nationalityCode);
}
