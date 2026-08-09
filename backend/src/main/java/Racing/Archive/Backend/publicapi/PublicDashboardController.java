package Racing.Archive.Backend.publicapi;

import Racing.Archive.Backend.domain.ChampionshipRound;
import Racing.Archive.Backend.domain.ResultStatus;
import Racing.Archive.Backend.domain.RaceSession;
import Racing.Archive.Backend.domain.SessionResult;
import Racing.Archive.Backend.domain.Season;
import Racing.Archive.Backend.repository.ChampionshipRoundRepository;
import Racing.Archive.Backend.repository.RaceSessionRepository;
import Racing.Archive.Backend.repository.ScoringSystemPositionRepository;
import Racing.Archive.Backend.repository.SeasonRepository;
import Racing.Archive.Backend.repository.SessionResultRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
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
    private final SessionResultRepository resultRepository;
    private final ScoringSystemPositionRepository scoringPositionRepository;

    @GetMapping("/dashboard")
    List<PublicSeasonResponse> dashboard() {
        return seasonRepository.findAll().stream().filter(Season::isActive).map(this::season).toList();
    }

    @GetMapping("/rounds/{roundId}/sessions")
    List<PublicSessionResponse> sessions(@org.springframework.web.bind.annotation.PathVariable Long roundId) {
        return sessionRepository.findAll().stream().filter(session -> session.getRound().getId().equals(roundId)).sorted(Comparator.comparing(RaceSession::getSessionOrder)).map(session -> new PublicSessionResponse(session.getId(), session.getName(), session.getType().name(), session.getSessionOrder())).toList();
    }

    @GetMapping("/sessions/{sessionId}/results")
    List<PublicSessionResultResponse> sessionResults(@org.springframework.web.bind.annotation.PathVariable Long sessionId) {
        List<SessionResult> results = resultRepository.findBySessionIdOrderByPositionAsc(sessionId);
        Map<String, List<SessionResult>> byClass = results.stream().filter(result -> result.getStatus() == ResultStatus.FINISHED && result.getPosition() != null).collect(java.util.stream.Collectors.groupingBy(result -> result.getEntry().getCompetitionClass().getCode()));
        Map<Long, Integer> classPositions = new HashMap<>();
        byClass.values().forEach(classResults -> { List<SessionResult> ordered = classResults.stream().sorted(Comparator.comparing(SessionResult::getPosition)).toList(); for (int index = 0; index < ordered.size(); index++) classPositions.put(ordered.get(index).getId(), index + 1); });
        return results.stream().map(result -> new PublicSessionResultResponse(result.getEntry().getDriver().getId(), result.getEntry().getDriver().getFirstName() + " " + result.getEntry().getDriver().getLastName(), result.getEntry().getCompetitionClass().getCode(), classPositions.get(result.getId()), result.getElapsedTimeMillis(), result.getStatus().name())).toList();
    }

    @GetMapping("/drivers/{driverId}/results")
    PublicDriverProfileResponse driverResults(@org.springframework.web.bind.annotation.PathVariable Long driverId) {
        List<SessionResult> results = resultRepository.findAll().stream().filter(result -> result.getEntry().getDriver().getId().equals(driverId)).sorted(Comparator.comparing((SessionResult result) -> result.getSession().getRound().getStartsOn(), Comparator.nullsLast(Comparator.reverseOrder()))).toList();
        String name = results.isEmpty() ? "Vozač" : results.getFirst().getEntry().getDriver().getFirstName() + " " + results.getFirst().getEntry().getDriver().getLastName();
        return new PublicDriverProfileResponse(name, results.stream().map(result -> new PublicDriverResultResponse(result.getSession().getRound().getName(), result.getSession().getName(), result.getEntry().getCompetitionClass().getCode(), classPosition(result), result.getElapsedTimeMillis(), result.getStatus().name())).toList());
    }

    private PublicSeasonResponse season(Season season) {
        List<ChampionshipRound> rounds = roundRepository.findAll().stream()
                .filter(round -> round.getSeason().getId().equals(season.getId()))
                .filter(round -> round.getStatus().name().equals("COMPLETED"))
                .sorted(Comparator.comparing(ChampionshipRound::getRoundNumber)).toList();
        Map<String, Map<Long, DriverScore>> byClass = new HashMap<>();
        Map<Integer, BigDecimal> points = season.getScoringSystem() == null ? Map.of() : scoringPositionRepository.findByScoringSystemId(season.getScoringSystem().getId()).stream().collect(java.util.stream.Collectors.toMap(value -> value.getPosition(), value -> value.getPoints()));
        for (ChampionshipRound round : rounds) scoreRound(season, round, points, byClass);
        return new PublicSeasonResponse(season.getId(), season.getChampionship().getName(), season.getChampionship().getShortName(), season.getYear(), rounds.stream().map(round -> new PublicRoundResponse(round.getId(), round.getRoundNumber(), round.getName(), round.getStartsOn())).toList(), byClass.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(entry -> new PublicClassStanding(entry.getKey(), standings(entry.getValue().values(), season))).toList());
    }

    private void scoreRound(Season season, ChampionshipRound round, Map<Integer, BigDecimal> points, Map<String, Map<Long, DriverScore>> byClass) {
        List<RaceSession> sessions = sessionRepository.findAll().stream().filter(session -> session.getRound().getId().equals(round.getId())).toList();
        RaceSession session = sessions.stream().filter(value -> value.getType().name().equals("RACE")).max(Comparator.comparing(RaceSession::getSessionOrder)).orElseGet(() -> sessions.stream().max(Comparator.comparing(RaceSession::getSessionOrder)).orElse(null));
        if (session == null) return;
        List<SessionResult> results = resultRepository.findBySessionIdOrderByPositionAsc(session.getId());
        Map<String, List<SessionResult>> byCompetitionClass = results.stream().collect(java.util.stream.Collectors.groupingBy(result -> result.getEntry().getCompetitionClass().getCode()));
        for (Map.Entry<String, List<SessionResult>> classResults : byCompetitionClass.entrySet()) {
            List<SessionResult> finishers = classResults.getValue().stream().filter(result -> result.getStatus() == ResultStatus.FINISHED && result.getPosition() != null).sorted(Comparator.comparing(SessionResult::getPosition)).toList();
            Map<Long, Integer> classPositions = new HashMap<>();
            for (int index = 0; index < finishers.size(); index++) classPositions.put(finishers.get(index).getId(), index + 1);
            for (SessionResult result : classResults.getValue()) {
                BigDecimal awarded = points.getOrDefault(classPositions.get(result.getId()), BigDecimal.ZERO);
                if (season.getScoringSystem() != null && finishers.size() < season.getScoringSystem().getMinimumCompetitorsForFullPoints() && !foreignHost(season, round)) awarded = awarded.multiply(BigDecimal.valueOf(season.getScoringSystem().getReducedPointsPercentage())).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                DriverScore score = byClass.computeIfAbsent(classResults.getKey(), ignored -> new HashMap<>()).computeIfAbsent(result.getEntry().getDriver().getId(), ignored -> new DriverScore(result.getEntry().getDriver().getId(), result.getEntry().getDriver().getFirstName() + " " + result.getEntry().getDriver().getLastName()));
                score.results.add(new ScoredResult(awarded, result.getStatus()));
            }
        }
    }

    private boolean foreignHost(Season season, ChampionshipRound round) { return round.getHostFederation() != null && !round.getHostFederation().getId().equals(season.getChampionship().getFederation().getId()) && season.getScoringSystem().isAwardFullPointsForForeignHost(); }
    private Integer classPosition(SessionResult result) { if (result.getStatus() != ResultStatus.FINISHED || result.getPosition() == null) return null; return (int) resultRepository.findBySessionIdOrderByPositionAsc(result.getSession().getId()).stream().filter(value -> value.getStatus() == ResultStatus.FINISHED && value.getPosition() != null).filter(value -> value.getEntry().getCompetitionClass().getId().equals(result.getEntry().getCompetitionClass().getId())).filter(value -> value.getPosition() <= result.getPosition()).count(); }
    private List<PublicStandingResponse> standings(java.util.Collection<DriverScore> values, Season season) { return values.stream().map(score -> { BigDecimal raw = score.results.stream().map(value -> value.points).reduce(BigDecimal.ZERO, BigDecimal::add); BigDecimal adjusted = raw; List<ScoredResult> eligible = score.results.stream().filter(value -> season.getScoringSystem() == null || season.getScoringSystem().isDisqualifiedResultEligibleForDrop() || value.status != ResultStatus.DSQ).sorted(Comparator.comparing(value -> value.points)).toList(); if (season.getScoringSystem() != null && score.results.size() > season.getScoringSystem().getResultsToDrop()) for (int index = 0; index < Math.min(season.getScoringSystem().getResultsToDrop(), eligible.size()); index++) adjusted = adjusted.subtract(eligible.get(index).points); return new PublicStandingResponse(score.id, score.name, raw, adjusted); }).sorted(Comparator.comparing(PublicStandingResponse::adjustedPoints).reversed().thenComparing(PublicStandingResponse::rawPoints, Comparator.reverseOrder())).toList(); }
    private static class DriverScore { private final Long id; private final String name; private final List<ScoredResult> results = new ArrayList<>(); private DriverScore(Long id, String name) { this.id = id; this.name = name; } }
    private record ScoredResult(BigDecimal points, ResultStatus status) {}
}

record PublicSeasonResponse(Long id, String championshipName, String championshipShortName, Short year, List<PublicRoundResponse> completedRounds, List<PublicClassStanding> standings) {}
record PublicRoundResponse(Long id, Integer roundNumber, String name, java.time.LocalDate startsOn) {}
record PublicClassStanding(String classCode, List<PublicStandingResponse> drivers) {}
record PublicStandingResponse(Long driverId, String driverName, BigDecimal rawPoints, BigDecimal adjustedPoints) {}
record PublicSessionResponse(Long id, String name, String type, Integer sessionOrder) {}
record PublicSessionResultResponse(Long driverId, String driverName, String classCode, Integer position, Long elapsedTimeMillis, String status) {}
record PublicDriverResultResponse(String roundName, String sessionName, String classCode, Integer position, Long elapsedTimeMillis, String status) {}
record PublicDriverProfileResponse(String driverName, List<PublicDriverResultResponse> results) {}
