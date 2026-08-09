package Racing.Archive.Backend.admin;

import Racing.Archive.Backend.domain.Driver;
import Racing.Archive.Backend.domain.Entry;
import Racing.Archive.Backend.domain.EntryStatus;
import Racing.Archive.Backend.domain.RaceSession;
import Racing.Archive.Backend.domain.ResultStatus;
import Racing.Archive.Backend.domain.SessionResult;
import Racing.Archive.Backend.domain.Vehicle;
import Racing.Archive.Backend.repository.DriverRepository;
import Racing.Archive.Backend.repository.EntryRepository;
import Racing.Archive.Backend.repository.RaceSessionRepository;
import Racing.Archive.Backend.repository.SessionResultRepository;
import Racing.Archive.Backend.repository.VehicleRepository;
import Racing.Archive.Backend.repository.CompetitionClassRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Transactional
class DirectoryController {
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final SessionResultRepository sessionResultRepository;
    private final RaceSessionRepository raceSessionRepository;
    private final EntryRepository entryRepository;
    private final CompetitionClassRepository competitionClassRepository;

    @GetMapping("/drivers") List<DriverResponse> drivers() { return driverRepository.findAll().stream().map(this::driver).toList(); }
    @PostMapping("/drivers") @ResponseStatus(HttpStatus.CREATED) DriverResponse createDriver(@Valid @RequestBody DriverRequest request) { Driver value = new Driver(); apply(value, request); return driver(driverRepository.save(value)); }
    @PutMapping("/drivers/{id}") DriverResponse updateDriver(@PathVariable Long id, @Valid @RequestBody DriverRequest request) { Driver value = driverEntity(id); apply(value, request); return driver(value); }
    @DeleteMapping("/drivers/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteDriver(@PathVariable Long id) { driverRepository.delete(driverEntity(id)); }

    @GetMapping("/vehicles") List<VehicleResponse> vehicles() { return vehicleRepository.findAll().stream().map(this::vehicle).toList(); }
    @PostMapping("/vehicles") @ResponseStatus(HttpStatus.CREATED) VehicleResponse createVehicle(@Valid @RequestBody VehicleRequest request) { Vehicle value = new Vehicle(); apply(value, request); return vehicle(vehicleRepository.save(value)); }
    @PutMapping("/vehicles/{id}") VehicleResponse updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleRequest request) { Vehicle value = vehicleEntity(id); apply(value, request); return vehicle(value); }
    @DeleteMapping("/vehicles/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteVehicle(@PathVariable Long id) { vehicleRepository.delete(vehicleEntity(id)); }

    @GetMapping("/sessions/{sessionId}/results") List<SessionResultResponse> results(@PathVariable Long sessionId) { return sessionResultRepository.findBySessionIdOrderByPositionAsc(sessionId).stream().map(this::result).toList(); }
    @PostMapping("/sessions/{sessionId}/results") @ResponseStatus(HttpStatus.CREATED) SessionResultResponse createResult(@PathVariable Long sessionId, @Valid @RequestBody ManualSessionResultRequest request) {
        RaceSession session = raceSessionRepository.findById(sessionId).orElseThrow(() -> missing("Race session", sessionId));
        Entry entry = entryRepository.findByRoundIdAndDriverId(session.getRound().getId(), request.driverId()).orElseGet(() -> {
            Entry created = new Entry(); created.setRound(session.getRound()); created.setDriver(driverEntity(request.driverId())); created.setCompetitionClass(competitionClassRepository.findById(request.competitionClassId()).orElseThrow(() -> missing("Competition class", request.competitionClassId()))); created.setStatus(EntryStatus.ENTERED); return entryRepository.save(created);
        });
        entry.setVehicle(vehicleEntity(request.vehicleId()));
        SessionResult result = sessionResultRepository.findBySessionIdAndEntryId(sessionId, entry.getId()).orElseGet(SessionResult::new);
        result.setSession(session); result.setEntry(entry); result.setElapsedTimeMillis(request.elapsedTimeMillis()); result.setPosition(request.position()); result.setStatus(request.status());
        return result(sessionResultRepository.save(result));
    }
    @PutMapping("/session-results/{id}") SessionResultResponse updateResult(@PathVariable Long id, @Valid @RequestBody SessionResultUpdateRequest request) { SessionResult value = resultEntity(id); if (request.vehicleId() != null) value.getEntry().setVehicle(vehicleEntity(request.vehicleId())); if (request.elapsedTimeMillis() != null) value.setElapsedTimeMillis(request.elapsedTimeMillis()); return result(value); }
    @DeleteMapping("/session-results/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteResult(@PathVariable Long id) { sessionResultRepository.delete(resultEntity(id)); }

    private void apply(Driver value, DriverRequest request) { value.setFirstName(request.firstName()); value.setLastName(request.lastName()); value.setNationalityCode(request.nationalityCode()); }
    private void apply(Vehicle value, VehicleRequest request) { value.setManufacturer(request.manufacturer()); value.setModel(request.model()); value.setDisplayName(request.displayName()); }
    private Driver driverEntity(Long id) { return driverRepository.findById(id).orElseThrow(() -> missing("Driver", id)); }
    private Vehicle vehicleEntity(Long id) { return vehicleRepository.findById(id).orElseThrow(() -> missing("Vehicle", id)); }
    private SessionResult resultEntity(Long id) { return sessionResultRepository.findById(id).orElseThrow(() -> missing("Session result", id)); }
    private ResponseStatusException missing(String type, Long id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, type + " " + id + " was not found"); }
    private DriverResponse driver(Driver value) { return new DriverResponse(value.getId(), value.getFirstName(), value.getLastName(), value.getNationalityCode()); }
    private VehicleResponse vehicle(Vehicle value) { return new VehicleResponse(value.getId(), value.getManufacturer(), value.getModel(), value.getDisplayName()); }
    private SessionResultResponse result(SessionResult value) { return new SessionResultResponse(value.getId(), value.getEntry().getDriver().getFirstName() + " " + value.getEntry().getDriver().getLastName(), value.getEntry().getVehicle() == null ? null : value.getEntry().getVehicle().getId(), value.getPosition(), value.getElapsedTimeMillis(), value.getStatus().name()); }
}

record DriverRequest(@NotBlank String firstName, @NotBlank String lastName, String nationalityCode) {}
record DriverResponse(Long id, String firstName, String lastName, String nationalityCode) {}
record VehicleRequest(@NotBlank String manufacturer, String model, String displayName) {}
record VehicleResponse(Long id, String manufacturer, String model, String displayName) {}
record SessionResultUpdateRequest(Long vehicleId, Long elapsedTimeMillis) {}
record ManualSessionResultRequest(@NotNull Long driverId, @NotNull Long vehicleId, @NotNull Long competitionClassId, Long elapsedTimeMillis, Integer position, @NotNull ResultStatus status) {}
record SessionResultResponse(Long id, String driverName, Long vehicleId, Integer position, Long elapsedTimeMillis, String status) {}
