package Racing.Archive.Backend.admin;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
class SetupController {

    private final SetupService setupService;

    @GetMapping("/federations") List<FederationResponse> federations() { return setupService.findFederations(); }
    @PostMapping("/federations") @ResponseStatus(HttpStatus.CREATED) FederationResponse createFederation(@Valid @RequestBody FederationRequest request) { return setupService.createFederation(request); }
    @PutMapping("/federations/{id}") FederationResponse updateFederation(@PathVariable Long id, @Valid @RequestBody FederationRequest request) { return setupService.updateFederation(id, request); }
    @DeleteMapping("/federations/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteFederation(@PathVariable Long id) { setupService.deleteFederation(id); }

    @GetMapping("/disciplines") List<DisciplineResponse> disciplines() { return setupService.findDisciplines(); }
    @PostMapping("/disciplines") @ResponseStatus(HttpStatus.CREATED) DisciplineResponse createDiscipline(@Valid @RequestBody DisciplineRequest request) { return setupService.createDiscipline(request); }
    @PutMapping("/disciplines/{id}") DisciplineResponse updateDiscipline(@PathVariable Long id, @Valid @RequestBody DisciplineRequest request) { return setupService.updateDiscipline(id, request); }
    @DeleteMapping("/disciplines/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteDiscipline(@PathVariable Long id) { setupService.deleteDiscipline(id); }

    @GetMapping("/championships") List<ChampionshipResponse> championships() { return setupService.findChampionships(); }
    @PostMapping("/championships") @ResponseStatus(HttpStatus.CREATED) ChampionshipResponse createChampionship(@Valid @RequestBody ChampionshipRequest request) { return setupService.createChampionship(request); }
    @PutMapping("/championships/{id}") ChampionshipResponse updateChampionship(@PathVariable Long id, @Valid @RequestBody ChampionshipRequest request) { return setupService.updateChampionship(id, request); }
    @DeleteMapping("/championships/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteChampionship(@PathVariable Long id) { setupService.deleteChampionship(id); }

    @GetMapping("/seasons") List<SeasonResponse> seasons() { return setupService.findSeasons(); }
    @PostMapping("/seasons") @ResponseStatus(HttpStatus.CREATED) SeasonResponse createSeason(@Valid @RequestBody SeasonRequest request) { return setupService.createSeason(request); }
    @PutMapping("/seasons/{id}") SeasonResponse updateSeason(@PathVariable Long id, @Valid @RequestBody SeasonRequest request) { return setupService.updateSeason(id, request); }
    @DeleteMapping("/seasons/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteSeason(@PathVariable Long id) { setupService.deleteSeason(id); }

    @GetMapping("/competition-classes") List<CompetitionClassResponse> competitionClasses() { return setupService.findCompetitionClasses(); }
    @PostMapping("/competition-classes") @ResponseStatus(HttpStatus.CREATED) CompetitionClassResponse createCompetitionClass(@Valid @RequestBody CompetitionClassRequest request) { return setupService.createCompetitionClass(request); }
    @PutMapping("/competition-classes/{id}") CompetitionClassResponse updateCompetitionClass(@PathVariable Long id, @Valid @RequestBody CompetitionClassRequest request) { return setupService.updateCompetitionClass(id, request); }
    @DeleteMapping("/competition-classes/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteCompetitionClass(@PathVariable Long id) { setupService.deleteCompetitionClass(id); }

    @GetMapping("/rounds") List<ChampionshipRoundResponse> rounds() { return setupService.findChampionshipRounds(); }
    @PostMapping("/rounds") @ResponseStatus(HttpStatus.CREATED) ChampionshipRoundResponse createRound(@Valid @RequestBody ChampionshipRoundRequest request) { return setupService.createChampionshipRound(request); }
    @PutMapping("/rounds/{id}") ChampionshipRoundResponse updateRound(@PathVariable Long id, @Valid @RequestBody ChampionshipRoundRequest request) { return setupService.updateChampionshipRound(id, request); }
    @DeleteMapping("/rounds/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteRound(@PathVariable Long id) { setupService.deleteChampionshipRound(id); }

    @GetMapping("/sessions") List<RaceSessionResponse> sessions() { return setupService.findRaceSessions(); }
    @PostMapping("/sessions") @ResponseStatus(HttpStatus.CREATED) RaceSessionResponse createSession(@Valid @RequestBody RaceSessionRequest request) { return setupService.createRaceSession(request); }
    @PutMapping("/sessions/{id}") RaceSessionResponse updateSession(@PathVariable Long id, @Valid @RequestBody RaceSessionRequest request) { return setupService.updateRaceSession(id, request); }
    @DeleteMapping("/sessions/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void deleteSession(@PathVariable Long id) { setupService.deleteRaceSession(id); }
}
