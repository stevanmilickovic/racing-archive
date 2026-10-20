package Racing.Archive.Backend.publicapi;

import Racing.Archive.Backend.domain.Classification;
import Racing.Archive.Backend.domain.Championship;
import Racing.Archive.Backend.domain.ChampionshipRound;
import Racing.Archive.Backend.domain.ClassificationResult;
import Racing.Archive.Backend.domain.CompetitionClass;
import Racing.Archive.Backend.domain.Driver;
import Racing.Archive.Backend.domain.Entry;
import Racing.Archive.Backend.domain.Federation;
import Racing.Archive.Backend.domain.PublicationStatus;
import Racing.Archive.Backend.domain.ResultStatus;
import Racing.Archive.Backend.domain.ScoringSystem;
import Racing.Archive.Backend.domain.Season;
import Racing.Archive.Backend.repository.ClassificationResultRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
    private final Map<Integer, BigDecimal> schedule = Map.of(1, new BigDecimal("25"),
            2, new BigDecimal("18"), 3, new BigDecimal("15"), 4, new BigDecimal("12"));

    @BeforeEach
    void setup() {
        scoring.setMinimumCompetitorsForFullPoints(3);
        scoring.setReducedPointsPercentage(50);
        scoring.setResultsToDrop(1);
        scoring.setAwardFullPointsForForeignHost(true);
        season.setScoringSystem(scoring);
        Championship championship = new Championship();
        Federation federation = new Federation();
        federation.setId(1L);
        championship.setFederation(federation);
        season.setChampionship(championship);
    }

    @Test
    void usesPublishedFractionalPointsWithoutReducingThemAgainOrDroppingTheOnlyResult() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED))
                .thenReturn(List.of(result(1L, "H 1000", "12.5", ResultStatus.FINISHED)));
        PublicStandingResponse standing = service.standings(season, roundIds, schedule).getFirst().drivers().getFirst();
        assertThat(standing.rawPoints()).isEqualByComparingTo("12.5");
        assertThat(standing.adjustedPoints()).isEqualByComparingTo("12.5");
        assertThat(standing.hasCalculatedPoints()).isFalse();
    }

    @Test
    void sumsOfficialPointsAndDropsTheLowestEligibleResult() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                result(1L, "H 1000", "25", ResultStatus.FINISHED),
                result(1L, "H 1000", "18", ResultStatus.FINISHED),
                result(1L, "H 1000", "0", ResultStatus.DSQ)));
        PublicStandingResponse standing = service.standings(season, roundIds, schedule).getFirst().drivers().getFirst();
        assertThat(standing.rawPoints()).isEqualByComparingTo("43");
        assertThat(standing.adjustedPoints()).isEqualByComparingTo("25");
        scoring.setDisqualifiedResultEligibleForDrop(true);
        assertThat(service.standings(season, roundIds, schedule).getFirst().drivers().getFirst().adjustedPoints())
                .isEqualByComparingTo("43");
    }

    @Test
    void preservesActualZeroAndShowsMissingOfficialPointsAsUnavailable() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                result(1L, "H 1000", null, ResultStatus.FINISHED),
                result(2L, "H 1000", "0", ResultStatus.DNF)));
        List<PublicStandingResponse> standing = service.standings(season, roundIds, schedule).getFirst().drivers();
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
        assertThat(service.standings(season, roundIds, schedule).getFirst().drivers().getFirst().rawPoints()).isNull();
    }

    @Test
    void keepsClassesAndSameNameDriversSeparateAndHidesPointsWithoutKnownSeasonRules() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                result(1L, "H 1000", "25", ResultStatus.FINISHED),
                result(2L, "H 1000", "18", ResultStatus.FINISHED),
                result(1L, "N 1400", "12", ResultStatus.FINISHED)));
        List<PublicClassStanding> groups = service.standings(season, roundIds, schedule);
        assertThat(groups).extracting(PublicClassStanding::classCode).containsExactly("H 1000", "N 1400");
        assertThat(groups.getFirst().drivers()).extracting(PublicStandingResponse::driverId).containsExactly(1L, 2L);
        assertThat(groups.get(1).drivers().getFirst().rawPoints()).isEqualByComparingTo("12");
        season.setScoringSystem(null);
        service.standings(season, roundIds, Map.of()).forEach(group -> group.drivers().forEach(driver -> {
            assertThat(driver.rawPoints()).isNull();
            assertThat(driver.adjustedPoints()).isNull();
        }));
    }

    @Test
    void seasonWithoutCompletedRoundsHasNoStandings() {
        assertThat(service.standings(season, List.of(), schedule)).isEmpty();
        verifyNoInteractions(repository);
    }

    @Test
    void combinesPublishedKotorPointsWithCalculatedGocClassPointsBeforeApplyingDrops() {
        Classification goc = classification(2L, 2L);
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                result(1L, "A 1150", "25", ResultStatus.FINISHED), // Petar
                result(2L, "A 1150", "0", ResultStatus.DNF), // Dragan
                result(3L, "A 1150", "0", ResultStatus.DNF), // Bozidar
                result(4L, "A 1150", "0", ResultStatus.DSQ), // Sava
                result(5L, "A 1150", "18", ResultStatus.FINISHED), // Zarko
                result(6L, "A 1150", "0", ResultStatus.DNF), // Milan
                result(7L, "A 1150", "0", ResultStatus.DNF), // Spasoje
                ranked(2L, 1, goc), ranked(3L, 2, goc), ranked(1L, 3, goc), ranked(4L, 4, goc)));
        var standings = service.standings(season, roundIds, schedule).getFirst().drivers();
        assertThat(standings).extracting(PublicStandingResponse::driverId).containsExactly(1L, 2L, 3L, 5L, 4L, 6L, 7L);
        assertThat(standings.getFirst().rawPoints()).isEqualByComparingTo("40");
        assertThat(standings.getFirst().adjustedPoints()).isEqualByComparingTo("25");
        assertThat(standings.getFirst().hasCalculatedPoints()).isTrue();
        assertThat(standings.get(1).rawPoints()).isEqualByComparingTo("25");
        assertThat(standings.get(1).adjustedPoints()).isEqualByComparingTo("25");
        assertThat(standings.get(2).rawPoints()).isEqualByComparingTo("18");
        assertThat(standings.get(3).hasCalculatedPoints()).isFalse();
        assertThat(standings.get(4).rawPoints()).isEqualByComparingTo("12");
        assertThat(standings.get(4).adjustedPoints()).isEqualByComparingTo("0");
    }

    @Test
    void reducesOnlyCalculatedPointsForASmallDomesticClassAndNeverMutatesPublishedData() {
        Classification domestic = classification(1L, 1L);
        ClassificationResult published = ranked(2L, 2, domestic);
        published.setPoints(new BigDecimal("9"));
        ClassificationResult calculated = ranked(1L, 1, domestic);
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(calculated, published));
        var standings = service.standings(season, roundIds, schedule).getFirst().drivers();
        assertThat(standings.getFirst().rawPoints()).isEqualByComparingTo("12.5");
        assertThat(standings.getFirst().hasCalculatedPoints()).isTrue();
        assertThat(standings.get(1).rawPoints()).isEqualByComparingTo("9");
        assertThat(standings.get(1).hasCalculatedPoints()).isFalse();
        assertThat(calculated.getPoints()).isNull();
        assertThat(published.getPoints()).isEqualByComparingTo("9");
    }

    @Test
    void countsFinishersWithinEachClassificationNotAcrossEventsOrClasses() {
        Classification first = classification(1L, 1L);
        Classification second = classification(2L, 1L);
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                ranked(1L, 1, first), ranked(2L, 2, first), ranked(1L, 1, second), ranked(2L, 2, second)));
        var standings = service.standings(season, roundIds, schedule).getFirst().drivers();
        assertThat(standings.getFirst().rawPoints()).isEqualByComparingTo("25");
        assertThat(standings.getFirst().adjustedPoints()).isEqualByComparingTo("12.5");
        assertThat(standings.get(1).rawPoints()).isEqualByComparingTo("18");
    }

    @Test
    void foreignHostExceptionUsesTheDisplayedSeasonNotTheRoundsPrimarySeason() {
        Classification foreign = classification(2L, 2L);
        foreign.getRound().setSeason(new Season());
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(ranked(1L, 1, foreign)));
        assertThat(service.standings(season, roundIds, schedule).getFirst().drivers().getFirst().rawPoints())
                .isEqualByComparingTo("25");
        scoring.setAwardFullPointsForForeignHost(false);
        assertThat(service.standings(season, roundIds, schedule).getFirst().drivers().getFirst().rawPoints())
                .isEqualByComparingTo("12.5");
    }

    @Test
    void unresolvedHostOrMissingPositionRemainUnknownButNonFinishersAndUnpaidPositionsGetZero() {
        Classification unknownHost = classification(1L, null);
        ClassificationResult missingPosition = ranked(2L, 1, unknownHost);
        missingPosition.setPosition(null);
        ClassificationResult dnf = result(3L, "A 1150", null, ResultStatus.DNF);
        ClassificationResult dns = result(4L, "A 1150", null, ResultStatus.DNS);
        ClassificationResult dsq = result(5L, "A 1150", null, ResultStatus.DSQ);
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED)).thenReturn(List.of(
                ranked(1L, 1, unknownHost), missingPosition, dnf, dns, dsq, ranked(6L, 11, unknownHost)));
        var standings = service.standings(season, roundIds, schedule).getFirst().drivers();
        assertThat(standings.subList(0, 4)).allSatisfy(row -> {
            assertThat(row.rawPoints()).isEqualByComparingTo("0");
            assertThat(row.hasCalculatedPoints()).isTrue();
        });
        assertThat(standings.subList(4, 6)).allSatisfy(row -> assertThat(row.rawPoints()).isNull());
    }

    @Test
    void emptyScheduleDoesNotTurnMissingPointsIntoZeroEvenWithAScoringSystem() {
        when(repository.findClassResults(roundIds, PublicationStatus.FINALIZED))
                .thenReturn(List.of(ranked(1L, 1, classification(1L, 1L))));
        var standing = service.standings(season, roundIds, Map.of()).getFirst().drivers().getFirst();
        assertThat(standing.rawPoints()).isNull();
        assertThat(standing.hasCalculatedPoints()).isFalse();
    }

    private Classification classification(Long roundId, Long hostFederationId) {
        ChampionshipRound round = new ChampionshipRound();
        round.setId(roundId);
        if (hostFederationId != null) {
            Federation host = new Federation();
            host.setId(hostFederationId);
            round.setHostFederation(host);
        }
        CompetitionClass competitionClass = new CompetitionClass();
        competitionClass.setCode("A 1150");
        Classification classification = new Classification();
        classification.setCompetitionClass(competitionClass);
        classification.setRound(round);
        return classification;
    }

    private ClassificationResult ranked(Long driverId, int position, Classification classification) {
        ClassificationResult result = result(driverId, "A 1150", null, ResultStatus.FINISHED);
        result.setClassification(classification);
        result.setPosition(position);
        return result;
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
