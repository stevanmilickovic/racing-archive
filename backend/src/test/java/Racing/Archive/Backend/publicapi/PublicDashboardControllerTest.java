package Racing.Archive.Backend.publicapi;

import Racing.Archive.Backend.domain.*;
import Racing.Archive.Backend.repository.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PublicDashboardControllerTest {
    private final SeasonRepository seasons = mock(SeasonRepository.class);
    private final ChampionshipRoundRepository rounds = mock(ChampionshipRoundRepository.class);
    private final RaceSessionRepository sessions = mock(RaceSessionRepository.class);
    private final RoundSeasonParticipationRepository participations = mock(RoundSeasonParticipationRepository.class);
    private final SessionResultRepository results = mock(SessionResultRepository.class);
    private final ScoringSystemPositionRepository points = mock(ScoringSystemPositionRepository.class);
    private final DriverRepository drivers = mock(DriverRepository.class);
    private final SeasonStandingsService standings = mock(SeasonStandingsService.class);
    private final PublicDashboardController controller = new PublicDashboardController(
            seasons, rounds, sessions, participations, results, points, drivers, standings);
    private final Driver first = driver(1L);
    private final Driver second = driver(2L);
    private final ChampionshipRound round = new ChampionshipRound();
    private final RaceSession session = new RaceSession();

    @BeforeEach
    void setup() {
        round.setId(1L);
        round.setName("Reviewed event");
        round.setStartsOn(LocalDate.of(2026, 5, 16));
        round.setRoundNumber(1);
        round.setStatus(RoundStatus.COMPLETED);
        session.setId(1L);
        session.setRound(round);
        session.setName("Race");
        session.setType(SessionType.RACE);
        session.setSessionOrder(1);
    }

    @Test
    void incompleteEntriesRemainVisibleAndSameNameDriversStayDistinct() {
        SessionResult firstResult = result(1L, first, null);
        SessionResult secondResult = result(2L, second, null);
        when(results.findBySessionIdOrderByPositionAsc(1L)).thenReturn(List.of(firstResult, secondResult));
        List<PublicSessionResultResponse> published = controller.sessionResults(1L);
        assertThat(published).extracting(PublicSessionResultResponse::driverId).containsExactly(1L, 2L);
        assertThat(published).allSatisfy(row -> {
            assertThat(row.classCode()).isNull();
            assertThat(row.position()).isNull();
            assertThat(row.clubName()).isNull();
            assertThat(row.vehicleName()).isNull();
            assertThat(row.elapsedTimeMillis()).isEqualTo(60123L);
        });
        when(drivers.findById(1L)).thenReturn(java.util.Optional.of(first));
        when(results.findAll()).thenReturn(List.of(firstResult, secondResult));
        PublicDriverProfileResponse profile = controller.driverResults(1L);
        assertThat(profile.results()).hasSize(1);
        assertThat(profile.results().getFirst().classCode()).isNull();
    }

    @Test
    void unknownClassDoesNotChangeKnownSessionClassPositions() {
        CompetitionClass competitionClass = new CompetitionClass();
        competitionClass.setId(1L);
        competitionClass.setCode("H 1000");
        SessionResult unknown = result(1L, first, null);
        SessionResult known = result(2L, second, competitionClass);
        known.setPosition(2);
        when(results.findBySessionIdOrderByPositionAsc(1L)).thenReturn(List.of(unknown, known));
        assertThat(controller.sessionResults(1L).get(1).position()).isEqualTo(1);
    }

    @Test
    void scoringSystemWithoutAPointsScheduleRemainsUnknown() {
        Championship championship = new Championship();
        championship.setName("Test championship");
        Season season = new Season();
        season.setId(1L);
        season.setYear((short) 2026);
        season.setActive(true);
        season.setChampionship(championship);
        ScoringSystem scoring = new ScoringSystem();
        scoring.setId(1L);
        season.setScoringSystem(scoring);
        RoundSeasonParticipation participation = new RoundSeasonParticipation();
        participation.setRound(round);
        when(seasons.findAll()).thenReturn(List.of(season));
        when(participations.findBySeasonId(1L)).thenReturn(List.of(participation));
        assertThat(controller.dashboard().getFirst().scoringKnown()).isFalse();
        verify(standings).standings(season, List.of(1L), java.util.Map.of());
        verifyNoInteractions(results);
    }

    private Driver driver(Long id) {
        Driver driver = new Driver();
        driver.setId(id);
        driver.setFirstName("Same");
        driver.setLastName("Name");
        return driver;
    }

    private SessionResult result(Long id, Driver driver, CompetitionClass competitionClass) {
        Entry entry = new Entry();
        entry.setDriver(driver);
        entry.setRound(round);
        entry.setCompetitionClass(competitionClass);
        SessionResult result = new SessionResult();
        result.setId(id);
        result.setSession(session);
        result.setEntry(entry);
        result.setElapsedTimeMillis(60123L);
        result.setPosition(1);
        result.setStatus(ResultStatus.FINISHED);
        return result;
    }
}
