package Racing.Archive.Backend.publicapi;

import Racing.Archive.Backend.domain.Classification;
import Racing.Archive.Backend.domain.ClassificationResult;
import Racing.Archive.Backend.domain.CompetitionClass;
import Racing.Archive.Backend.domain.Driver;
import Racing.Archive.Backend.domain.Entry;
import Racing.Archive.Backend.domain.PublicationStatus;
import Racing.Archive.Backend.domain.ResultStatus;
import Racing.Archive.Backend.domain.ScoringSystem;
import Racing.Archive.Backend.domain.Season;
import Racing.Archive.Backend.repository.ClassificationResultRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeasonStandingsServiceTest {
    private final ClassificationResultRepository repository = mock(ClassificationResultRepository.class);
    private final SeasonStandingsService service = new SeasonStandingsService(repository);
    private final Season season = new Season();
    private final ScoringSystem scoring = new ScoringSystem();
    private final List<Long> roundIds = List.of(1L, 2L, 3L);

    @BeforeEach
    void setup() {
        scoring.setMinimumCompetitorsForFullPoints(3);
        scoring.setReducedPointsPercentage(50);
        scoring.setResultsToDrop(1);
        season.setScoringSystem(scoring);
    }

    @Test
    void usesPublishedFractionalPointsWithoutReducingThemAgainOrDroppingTheOnlyResult() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED))
                .thenReturn(List.of(result(1L, "H 1000", "12.5", ResultStatus.FINISHED)));
        PublicStandingResponse standing = service.standings(season, roundIds, true).getFirst().drivers().getFirst();
        assertThat(standing.rawPoints()).isEqualByComparingTo("12.5");
        assertThat(standing.adjustedPoints()).isEqualByComparingTo("12.5");
    }

    @Test
    void sumsOfficialPointsAndDropsTheLowestEligibleResult() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                result(1L, "H 1000", "25", ResultStatus.FINISHED),
                result(1L, "H 1000", "18", ResultStatus.FINISHED),
                result(1L, "H 1000", "0", ResultStatus.DSQ)));
        PublicStandingResponse standing = service.standings(season, roundIds, true).getFirst().drivers().getFirst();
        assertThat(standing.rawPoints()).isEqualByComparingTo("43");
        assertThat(standing.adjustedPoints()).isEqualByComparingTo("25");
        scoring.setDisqualifiedResultEligibleForDrop(true);
        assertThat(service.standings(season, roundIds, true).getFirst().drivers().getFirst().adjustedPoints())
                .isEqualByComparingTo("43");
    }

    @Test
    void preservesActualZeroAndShowsMissingOfficialPointsAsUnavailable() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                result(1L, "H 1000", null, ResultStatus.FINISHED),
                result(2L, "H 1000", "0", ResultStatus.DNF)));
        List<PublicStandingResponse> standing = service.standings(season, roundIds, true).getFirst().drivers();
        assertThat(standing.getFirst().driverId()).isEqualTo(2L);
        assertThat(standing.getFirst().rawPoints()).isEqualByComparingTo("0");
        assertThat(standing.get(1).rawPoints()).isNull();
        assertThat(standing.get(1).adjustedPoints()).isNull();
    }

    @Test
    void partialTotalIsNotPublishedAsComplete() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                result(1L, "H 1000", "25", ResultStatus.FINISHED),
                result(1L, "H 1000", null, ResultStatus.FINISHED)));
        assertThat(service.standings(season, roundIds, true).getFirst().drivers().getFirst().rawPoints()).isNull();
    }

    @Test
    void keepsClassesAndSameNameDriversSeparateAndHidesPointsWithoutKnownSeasonRules() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                result(1L, "H 1000", "25", ResultStatus.FINISHED),
                result(2L, "H 1000", "18", ResultStatus.FINISHED),
                result(1L, "N 1400", "12", ResultStatus.FINISHED)));
        List<PublicClassStanding> groups = service.standings(season, roundIds, true);
        assertThat(groups).extracting(PublicClassStanding::classCode).containsExactly("H 1000", "N 1400");
        assertThat(groups.getFirst().drivers()).extracting(PublicStandingResponse::driverId).containsExactly(1L, 2L);
        assertThat(groups.get(1).drivers().getFirst().rawPoints()).isEqualByComparingTo("12");
        season.setScoringSystem(null);
        service.standings(season, roundIds, false).forEach(group -> group.drivers().forEach(driver -> {
            assertThat(driver.rawPoints()).isNull();
            assertThat(driver.adjustedPoints()).isNull();
        }));
    }

    @Test
    void seasonWithoutCompletedRoundsHasNoStandings() {
        assertThat(service.standings(season, List.of(), true)).isEmpty();
        verifyNoInteractions(repository);
    }

    private ClassificationResult result(Long driverId, String classCode, String points, ResultStatus status) {
        Driver driver = new Driver();
        driver.setId(driverId);
        driver.setFirstName("Same");
        driver.setLastName("Name");
        Entry entry = new Entry();
        entry.setDriver(driver);
        CompetitionClass competitionClass = new CompetitionClass();
        competitionClass.setCode(classCode);
        Classification classification = new Classification();
        classification.setCompetitionClass(competitionClass);
        ClassificationResult result = new ClassificationResult();
        result.setClassification(classification);
        result.setEntry(entry);
        result.setStatus(status);
        result.setPoints(points == null ? null : new BigDecimal(points));
        return result;
    }
}
