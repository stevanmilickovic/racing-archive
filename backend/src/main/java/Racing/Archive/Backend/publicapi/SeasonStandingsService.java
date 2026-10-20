package Racing.Archive.Backend.publicapi;

import Racing.Archive.Backend.domain.ClassificationResult;
import Racing.Archive.Backend.domain.Driver;
import Racing.Archive.Backend.domain.PublicationStatus;
import Racing.Archive.Backend.domain.ResultStatus;
import Racing.Archive.Backend.domain.ScoringSystem;
import Racing.Archive.Backend.domain.Season;
import Racing.Archive.Backend.repository.ClassificationResultRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeasonStandingsService {
    private final ClassificationResultRepository results;

    List<PublicClassStanding> standings(Season season, List<Long> roundIds, boolean scoringKnown) {
        if (roundIds.isEmpty()) return List.of();
        Map<String, Map<Long, DriverScore>> classes = new TreeMap<>();
        for (ClassificationResult result : results.findClassResults(roundIds, PublicationStatus.FINALIZED)) {
            String classCode = result.getClassification().getCompetitionClass().getCode();
            Driver driver = result.getEntry().getDriver();
            DriverScore score = classes.computeIfAbsent(classCode, ignored -> new HashMap<>())
                    .computeIfAbsent(driver.getId(), ignored -> new DriverScore(driver));
            score.results.add(result);
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
        // Missing official points must not be presented as an actual zero score.
        if (!scoringKnown || score.results.stream().anyMatch(result -> result.getPoints() == null)) {
            return new PublicStandingResponse(score.driver.getId(), name(score.driver), null, null);
        }
        BigDecimal raw = score.results.stream().map(ClassificationResult::getPoints)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal adjusted = raw;
        if (score.results.size() > scoring.getResultsToDrop()) {
            List<BigDecimal> dropped = score.results.stream()
                    .filter(result -> scoring.isDisqualifiedResultEligibleForDrop() || result.getStatus() != ResultStatus.DSQ)
                    .map(ClassificationResult::getPoints).sorted().limit(scoring.getResultsToDrop()).toList();
            for (BigDecimal points : dropped) adjusted = adjusted.subtract(points);
        }
        // Points already include the official class-size/foreign-host decisions.
        return new PublicStandingResponse(score.driver.getId(), name(score.driver), raw, adjusted);
    }

    private String name(Driver driver) {
        return driver.getFirstName() + " " + driver.getLastName();
    }

    private static class DriverScore {
        private final Driver driver;
        private final List<ClassificationResult> results = new ArrayList<>();

        private DriverScore(Driver driver) {
            this.driver = driver;
        }
    }
}
