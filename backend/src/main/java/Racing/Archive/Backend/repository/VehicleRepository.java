package Racing.Archive.Backend.repository;

import Racing.Archive.Backend.domain.Vehicle;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findFirstByManufacturerIgnoreCaseAndModelIgnoreCaseAndDisplayName(String manufacturer, String model, String displayName);
}
