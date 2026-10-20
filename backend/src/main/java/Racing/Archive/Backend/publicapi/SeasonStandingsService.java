package Racing.Archive.Backend.publicapi;

import Racing.Archive.Backend.domain.Classification;
import Racing.Archive.Backend.domain.ClassificationResult;
import Racing.Archive.Backend.domain.Driver;
import Racing.Archive.Backend.domain.PublicationStatus;
import Racing.Archive.Backend.domain.ResultStatus;
import Racing.Archive.Backend.domain.ScoringSystem;
import Racing.Archive.Backend.domain.Season;
import Racing.Archive.Backend.repository.ClassificationResultRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeasonStandingsService {
    private final ClassificationResultRepository results;

    List<PublicClassStanding> standings(Season season, List<Long> roundIds, Map<Integer, BigDecimal> schedule) {
        if (roundIds.isEmpty()) return List.of();
        boolean scoringKnown = season.getScoringSystem() != null && !schedule.isEmpty();
        Map<String, Map<Long, DriverScore>> classes = new TreeMap<>();
        Map<Classification, List<ClassificationResult>> classifications = results
                .findClassResults(roundIds, PublicationStatus.FINALIZED).stream()
                .collect(Collectors.groupingBy(ClassificationResult::getClassification));
        for (var classification : classifications.entrySet()) {
            String classCode = classification.getKey().getCompetitionClass().getCode();
            long finishers = classification.getValue().stream()
                    .filter(result -> result.getStatus() == ResultStatus.FINISHED && result.getPosition() != null)
                    .count();
            for (ClassificationResult result : classification.getValue()) {
                Driver driver = result.getEntry().getDriver();
                DriverScore score = classes.computeIfAbsent(classCode, ignored -> new HashMap<>())
                        .computeIfAbsent(driver.getId(), ignored -> new DriverScore(driver));
                score.results.add(points(result, season, schedule, scoringKnown, finishers));
            }
        }
        return classes.entrySet().stream().map(group -> new PublicClassStanding(group.getKey(),
                group.getValue().values().stream()
                        .map(score -> total(score, season.getScoringSystem(), scoringKnown))
                        .sorted(Comparator.comparing(PublicStandingResponse::adjustedPoints,
                                        Comparator.nullsLast(Comparator.reverseOrder()))
                                .thenComparing(PublicStandingResponse::rawPoints,
                                        Comparator.nullsLast(Comparator.reverseOrder()))
                                .thenComparing(PublicStandingResponse::driverName)
                                .thenComparing(PublicStandingResponse::driverId))
                        .toList())).toList();
    }

    private PublicStandingResponse total(DriverScore score, ScoringSystem scoring, boolean scoringKnown) {
        boolean calculated = score.results.stream().anyMatch(ScoredResult::calculated);
        // A missing position or unresolved scoring context is still unknown, not zero.
        if (!scoringKnown || score.results.stream().anyMatch(result -> result.points() == null)) {
            return new PublicStandingResponse(score.driver.getId(), name(score.driver), null, null, calculated);
        }
        BigDecimal raw = score.results.stream().map(ScoredResult::points)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal adjusted = raw;
        if (score.results.size() > scoring.getResultsToDrop()) {
            List<BigDecimal> dropped = score.results.stream()
                    .filter(result -> scoring.isDisqualifiedResultEligibleForDrop() || result.status() != ResultStatus.DSQ)
                    .map(ScoredResult::points).sorted().limit(scoring.getResultsToDrop()).toList();
            for (BigDecimal points : dropped) adjusted = adjusted.subtract(points);
        }
        return new PublicStandingResponse(score.driver.getId(), name(score.driver), raw, adjusted, calculated);
    }

    private ScoredResult points(ClassificationResult result, Season season, Map<Integer, BigDecimal> schedule,
                                boolean scoringKnown, long finishers) {
        if (!scoringKnown) return new ScoredResult(null, result.getStatus(), false);
        // Published points already include official reductions and always take precedence.
        if (result.getPoints() != null) return new ScoredResult(result.getPoints(), result.getStatus(), false);
        if (result.getStatus() == null) return new ScoredResult(null, null, false);
        if (result.getStatus() != ResultStatus.FINISHED) {
            return new ScoredResult(BigDecimal.ZERO, result.getStatus(), true);
        }
        if (result.getPosition() == null || result.getPosition() < 1) {
            return new ScoredResult(null, result.getStatus(), false);
        }
        BigDecimal awarded = schedule.getOrDefault(result.getPosition(), BigDecimal.ZERO);
        ScoringSystem scoring = season.getScoringSystem();
        if (awarded.signum() != 0 && finishers < scoring.getMinimumCompetitorsForFullPoints()) {
            Boolean foreignHost = foreignHost(season, result.getClassification());
            if (foreignHost == null) return new ScoredResult(null, result.getStatus(), false);
            if (!foreignHost) {
                awarded = awarded.multiply(BigDecimal.valueOf(scoring.getReducedPointsPercentage()))
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            }
        }
        return new ScoredResult(awarded, result.getStatus(), true);
    }

    private Boolean foreignHost(Season season, Classification classification) {
        if (!season.getScoringSystem().isAwardFullPointsForForeignHost()) return false;
        if (classification.getRound() == null || classification.getRound().getHostFederation() == null
                || season.getChampionship() == null || season.getChampionship().getFederation() == null) return null;
        Long host = classification.getRound().getHostFederation().getId();
        Long home = season.getChampionship().getFederation().getId();
        return host == null || home == null ? null : !host.equals(home);
    }

    private String name(Driver driver) {
        return driver.getFirstName() + " " + driver.getLastName();
    }

    private static class DriverScore {
        private final Driver driver;
        private final List<ScoredResult> results = new ArrayList<>();

        private DriverScore(Driver driver) {
            this.driver = driver;
        }
    }

    private record ScoredResult(BigDecimal points, ResultStatus status, boolean calculated) {}
}
