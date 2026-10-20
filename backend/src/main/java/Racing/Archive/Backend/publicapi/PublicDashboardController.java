package Racing.Archive.Backend.publicapi;

import Racing.Archive.Backend.domain.ChampionshipRound;
import Racing.Archive.Backend.domain.ResultStatus;
import Racing.Archive.Backend.domain.RaceSession;
import Racing.Archive.Backend.domain.SessionResult;
import Racing.Archive.Backend.domain.Season;
import Racing.Archive.Backend.domain.Entry;
import Racing.Archive.Backend.domain.Driver;
import Racing.Archive.Backend.repository.ChampionshipRoundRepository;
import Racing.Archive.Backend.repository.RaceSessionRepository;
import Racing.Archive.Backend.repository.RoundSeasonParticipationRepository;
import Racing.Archive.Backend.repository.ScoringSystemPositionRepository;
import Racing.Archive.Backend.repository.SeasonRepository;
import Racing.Archive.Backend.repository.SessionResultRepository;
import Racing.Archive.Backend.repository.DriverRepository;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublicDashboardController {
    private final SeasonRepository seasonRepository;
    private final ChampionshipRoundRepository roundRepository;
    private final RaceSessionRepository sessionRepository;
    private final RoundSeasonParticipationRepository roundSeasonParticipationRepository;
    private final SessionResultRepository resultRepository;
    private final ScoringSystemPositionRepository scoringPositionRepository;
    private final DriverRepository driverRepository;
    private final SeasonStandingsService standingsService;

    @GetMapping("/dashboard")
    List<PublicSeasonResponse> dashboard() {
        return seasonRepository.findAll().stream().filter(Season::isActive).map(this::season).toList();
    }

    @GetMapping("/rounds/{roundId}")
    PublicRoundDetailResponse round(@org.springframework.web.bind.annotation.PathVariable Long roundId) {
        ChampionshipRound round = roundRepository.findById(roundId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new PublicRoundDetailResponse(round.getId(), round.getName(), round.getStartsOn(),
                round.getEndsOn(), round.getTrack() == null ? null : round.getTrack().getName());
    }

    @GetMapping("/rounds/{roundId}/sessions")
    List<PublicSessionResponse> sessions(@org.springframework.web.bind.annotation.PathVariable Long roundId) {
        return sessionRepository.findAll().stream().filter(session -> session.getRound().getId().equals(roundId)).sorted(Comparator.comparing(RaceSession::getSessionOrder)).map(session -> new PublicSessionResponse(session.getId(), session.getName(), session.getType().name(), session.getSessionOrder())).toList();
    }

    @GetMapping("/sessions/{sessionId}/results")
    List<PublicSessionResultResponse> sessionResults(@org.springframework.web.bind.annotation.PathVariable Long sessionId) {
        List<SessionResult> results = resultRepository.findBySessionIdOrderByPositionAsc(sessionId);
        Map<String, List<SessionResult>> byClass = results.stream().filter(result -> result.getEntry().getCompetitionClass() != null).filter(result -> result.getStatus() == ResultStatus.FINISHED && result.getPosition() != null).collect(java.util.stream.Collectors.groupingBy(result -> result.getEntry().getCompetitionClass().getCode()));
        Map<Long, Integer> classPositions = new HashMap<>();
        byClass.values().forEach(classResults -> { List<SessionResult> ordered = classResults.stream().sorted(Comparator.comparing(SessionResult::getPosition)).toList(); for (int index = 0; index < ordered.size(); index++) classPositions.put(ordered.get(index).getId(), index + 1); });
        return results.stream().map(result -> new PublicSessionResultResponse(result.getEntry().getDriver().getId(), result.getEntry().getDriver().getFirstName() + " " + result.getEntry().getDriver().getLastName(), classCode(result.getEntry()), classPositions.get(result.getId()), result.getElapsedTimeMillis(), result.getStatus().name(), clubName(result.getEntry()), vehicleName(result.getEntry()))).toList();
    }

    @GetMapping("/drivers/{driverId}/results")
    PublicDriverProfileResponse driverResults(@org.springframework.web.bind.annotation.PathVariable Long driverId) {
        List<SessionResult> results = resultRepository.findAll().stream().filter(result -> result.getEntry().getDriver().getId().equals(driverId)).sorted(Comparator.comparing((SessionResult result) -> result.getSession().getRound().getStartsOn(), Comparator.nullsLast(Comparator.reverseOrder()))).toList();
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String name = driver.getFirstName() + " " + driver.getLastName();
        return new PublicDriverProfileResponse(name, results.stream().map(result -> new PublicDriverResultResponse(result.getSession().getRound().getId(), result.getSession().getRound().getName(), result.getSession().getName(), classCode(result.getEntry()), classPosition(result), result.getElapsedTimeMillis(), result.getStatus().name(), clubName(result.getEntry()), vehicleName(result.getEntry()))).toList());
    }

    private PublicSeasonResponse season(Season season) {
        List<ChampionshipRound> rounds = roundSeasonParticipationRepository.findBySeasonId(season.getId()).stream()
                .map(participation -> participation.getRound())
                .filter(round -> round.getStatus().name().equals("COMPLETED"))
                .sorted(Comparator.comparing(ChampionshipRound::getRoundNumber)).toList();
        Map<Integer, BigDecimal> schedule = season.getScoringSystem() == null ? Map.of()
                : scoringPositionRepository.findByScoringSystemId(season.getScoringSystem().getId()).stream()
                        .collect(java.util.stream.Collectors.toMap(value -> value.getPosition(), value -> value.getPoints()));
        boolean scoringKnown = season.getScoringSystem() != null && !schedule.isEmpty();
        List<PublicClassStanding> standings = standingsService.standings(season,
                rounds.stream().map(ChampionshipRound::getId).toList(), schedule);
        return new PublicSeasonResponse(season.getId(), season.getChampionship().getName(),
                season.getChampionship().getShortName(), season.getYear(), scoringKnown,
                rounds.stream().map(round -> new PublicRoundResponse(round.getId(), round.getRoundNumber(),
                        round.getName(), round.getStartsOn())).toList(), standings);
    }

    private String classCode(Entry entry) { return entry.getCompetitionClass() == null ? null : entry.getCompetitionClass().getCode(); }
    private String clubName(Entry entry) { return entry.getClub() == null ? null : entry.getClub().getName(); }
    private String vehicleName(Entry entry) {
        if (entry.getVehicle() == null) return null;
        return entry.getVehicle().getDisplayName() != null ? entry.getVehicle().getDisplayName()
                : entry.getVehicle().getManufacturer() + (entry.getVehicle().getModel() == null ? "" : " " + entry.getVehicle().getModel());
    }
    private Integer classPosition(SessionResult result) { if (result.getEntry().getCompetitionClass() == null || result.getStatus() != ResultStatus.FINISHED || result.getPosition() == null) return null; return (int) resultRepository.findBySessionIdOrderByPositionAsc(result.getSession().getId()).stream().filter(value -> value.getStatus() == ResultStatus.FINISHED && value.getPosition() != null).filter(value -> value.getEntry().getCompetitionClass() != null).filter(value -> value.getEntry().getCompetitionClass().getId().equals(result.getEntry().getCompetitionClass().getId())).filter(value -> value.getPosition() <= result.getPosition()).count(); }
}

record PublicSeasonResponse(Long id, String championshipName, String championshipShortName, Short year, boolean scoringKnown, List<PublicRoundResponse> completedRounds, List<PublicClassStanding> standings) {}
record PublicRoundResponse(Long id, Integer roundNumber, String name, java.time.LocalDate startsOn) {}
record PublicRoundDetailResponse(Long id, String name, java.time.LocalDate startsOn, java.time.LocalDate endsOn, String trackName) {}
record PublicClassStanding(String classCode, List<PublicStandingResponse> drivers) {}
record PublicStandingResponse(Long driverId, String driverName, BigDecimal rawPoints, BigDecimal adjustedPoints, boolean hasCalculatedPoints) {}
record PublicSessionResponse(Long id, String name, String type, Integer sessionOrder) {}
record PublicSessionResultResponse(Long driverId, String driverName, String classCode, Integer position, Long elapsedTimeMillis, String status, String clubName, String vehicleName) {}
record PublicDriverResultResponse(Long roundId, String roundName, String sessionName, String classCode, Integer position, Long elapsedTimeMillis, String status, String clubName, String vehicleName) {}
record PublicDriverProfileResponse(String driverName, List<PublicDriverResultResponse> results) {}
