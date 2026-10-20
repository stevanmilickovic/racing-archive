package Racing.Archive.Backend.admin;

import Racing.Archive.Backend.domain.Championship;
import Racing.Archive.Backend.domain.ChampionshipRound;
import Racing.Archive.Backend.domain.CompetitionClass;
import Racing.Archive.Backend.domain.Discipline;
import Racing.Archive.Backend.domain.Federation;
import Racing.Archive.Backend.domain.RaceSession;
import Racing.Archive.Backend.domain.RoundSeasonParticipation;
import Racing.Archive.Backend.domain.Season;
import Racing.Archive.Backend.repository.ChampionshipRepository;
import Racing.Archive.Backend.repository.ChampionshipRoundRepository;
import Racing.Archive.Backend.repository.CompetitionClassRepository;
import Racing.Archive.Backend.repository.DisciplineRepository;
import Racing.Archive.Backend.repository.FederationRepository;
import Racing.Archive.Backend.repository.RaceSessionRepository;
import Racing.Archive.Backend.repository.RoundSeasonParticipationRepository;
import Racing.Archive.Backend.repository.SeasonRepository;
import Racing.Archive.Backend.repository.ScoringSystemRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
class SetupService {

    private final FederationRepository federationRepository;
    private final DisciplineRepository disciplineRepository;
    private final ChampionshipRepository championshipRepository;
    private final SeasonRepository seasonRepository;
    private final CompetitionClassRepository competitionClassRepository;
    private final ChampionshipRoundRepository championshipRoundRepository;
    private final RaceSessionRepository raceSessionRepository;
    private final RoundSeasonParticipationRepository roundSeasonParticipationRepository;
    private final ScoringSystemRepository scoringSystemRepository;

    @Transactional(readOnly = true)
    List<FederationResponse> findFederations() { return federationRepository.findAll().stream().map(this::federationResponse).toList(); }
    FederationResponse createFederation(FederationRequest request) { Federation federation = new Federation(); apply(federation, request); return federationResponse(federationRepository.save(federation)); }
    FederationResponse updateFederation(Long id, FederationRequest request) { Federation federation = federation(id); apply(federation, request); return federationResponse(federation); }
    void deleteFederation(Long id) { federationRepository.delete(federation(id)); }

    @Transactional(readOnly = true)
    List<DisciplineResponse> findDisciplines() { return disciplineRepository.findAll().stream().map(this::disciplineResponse).toList(); }
    DisciplineResponse createDiscipline(DisciplineRequest request) { Discipline discipline = new Discipline(); apply(discipline, request); return disciplineResponse(disciplineRepository.save(discipline)); }
    DisciplineResponse updateDiscipline(Long id, DisciplineRequest request) { Discipline discipline = discipline(id); apply(discipline, request); return disciplineResponse(discipline); }
    void deleteDiscipline(Long id) { disciplineRepository.delete(discipline(id)); }

    @Transactional(readOnly = true)
    List<ChampionshipResponse> findChampionships() { return championshipRepository.findAll().stream().map(this::championshipResponse).toList(); }
    ChampionshipResponse createChampionship(ChampionshipRequest request) { Championship championship = new Championship(); apply(championship, request); return championshipResponse(championshipRepository.save(championship)); }
    ChampionshipResponse updateChampionship(Long id, ChampionshipRequest request) { Championship championship = championship(id); apply(championship, request); return championshipResponse(championship); }
    void deleteChampionship(Long id) { championshipRepository.delete(championship(id)); }

    @Transactional(readOnly = true)
    List<SeasonResponse> findSeasons() { return seasonRepository.findAll().stream().map(this::seasonResponse).toList(); }
    SeasonResponse createSeason(SeasonRequest request) { Season season = new Season(); apply(season, request); return seasonResponse(seasonRepository.save(season)); }
    SeasonResponse updateSeason(Long id, SeasonRequest request) { Season season = season(id); apply(season, request); return seasonResponse(season); }
    void deleteSeason(Long id) { seasonRepository.delete(season(id)); }

    @Transactional(readOnly = true)
    List<CompetitionClassResponse> findCompetitionClasses() { return competitionClassRepository.findAll().stream().map(this::competitionClassResponse).toList(); }
    CompetitionClassResponse createCompetitionClass(CompetitionClassRequest request) { CompetitionClass competitionClass = new CompetitionClass(); apply(competitionClass, request); return competitionClassResponse(competitionClassRepository.save(competitionClass)); }
    CompetitionClassResponse updateCompetitionClass(Long id, CompetitionClassRequest request) { CompetitionClass competitionClass = competitionClass(id); apply(competitionClass, request); return competitionClassResponse(competitionClass); }
    void deleteCompetitionClass(Long id) { competitionClassRepository.delete(competitionClass(id)); }

    @Transactional(readOnly = true)
    List<ChampionshipRoundResponse> findChampionshipRounds() { return championshipRoundRepository.findAll().stream().map(this::championshipRoundResponse).toList(); }
    ChampionshipRoundResponse createChampionshipRound(ChampionshipRoundRequest request) { ChampionshipRound round = new ChampionshipRound(); apply(round, request); round = championshipRoundRepository.save(round); syncSeasonParticipations(round, request); return championshipRoundResponse(round); }
    ChampionshipRoundResponse updateChampionshipRound(Long id, ChampionshipRoundRequest request) { ChampionshipRound round = championshipRound(id); apply(round, request); syncSeasonParticipations(round, request); return championshipRoundResponse(round); }
    void deleteChampionshipRound(Long id) { roundSeasonParticipationRepository.deleteByRoundId(id); championshipRoundRepository.delete(championshipRound(id)); }

    @Transactional(readOnly = true)
    List<RaceSessionResponse> findRaceSessions() { return raceSessionRepository.findAll().stream().map(this::raceSessionResponse).toList(); }
    RaceSessionResponse createRaceSession(RaceSessionRequest request) { RaceSession session = new RaceSession(); apply(session, request); return raceSessionResponse(raceSessionRepository.save(session)); }
    RaceSessionResponse updateRaceSession(Long id, RaceSessionRequest request) { RaceSession session = raceSession(id); apply(session, request); return raceSessionResponse(session); }
    void deleteRaceSession(Long id) { raceSessionRepository.delete(raceSession(id)); }

    private void apply(Federation federation, FederationRequest request) { federation.setName(request.name()); federation.setCountryCode(request.countryCode()); }
    private void apply(Discipline discipline, DisciplineRequest request) { discipline.setCode(request.code()); discipline.setName(request.name()); }
    private void apply(Championship championship, ChampionshipRequest request) { championship.setFederation(federation(request.federationId())); championship.setDiscipline(discipline(request.disciplineId())); championship.setName(request.name()); championship.setShortName(request.shortName()); }
    private void apply(Season season, SeasonRequest request) { season.setChampionship(championship(request.championshipId())); season.setYear(request.year()); season.setName(request.name()); season.setActive(request.active()); season.setScoringSystem(request.scoringSystemId() == null ? null : scoringSystemRepository.findById(request.scoringSystemId()).orElseThrow(() -> notFound("Scoring system", request.scoringSystemId()))); }
    private void apply(CompetitionClass competitionClass, CompetitionClassRequest request) { competitionClass.setCode(request.code()); }
    private void apply(ChampionshipRound round, ChampionshipRoundRequest request) { round.setSeason(season(request.seasonId())); round.setHostFederation(round.getSeason().getChampionship().getFederation()); round.setRoundNumber(request.roundNumber()); round.setName(request.name()); round.setStartsOn(request.startsOn()); round.setEndsOn(request.endsOn()); round.setStatus(request.status()); }
    private void syncSeasonParticipations(ChampionshipRound round, ChampionshipRoundRequest request) { roundSeasonParticipationRepository.deleteByRoundId(round.getId()); roundSeasonParticipationRepository.flush(); List<Long> seasonIds = java.util.stream.Stream.concat(java.util.stream.Stream.of(round.getSeason().getId()), request.guestSeasonIds() == null ? java.util.stream.Stream.empty() : request.guestSeasonIds().stream()).filter(Objects::nonNull).distinct().toList(); for (Long seasonId : seasonIds) { RoundSeasonParticipation participation = new RoundSeasonParticipation(); participation.setRound(round); participation.setSeason(season(seasonId)); roundSeasonParticipationRepository.save(participation); } }
    private void apply(RaceSession session, RaceSessionRequest request) { session.setRound(championshipRound(request.roundId())); session.setName(request.name()); session.setType(request.type()); session.setSessionOrder(request.sessionOrder()); session.setScheduledAt(request.scheduledAt()); }

    private Federation federation(Long id) { return federationRepository.findById(id).orElseThrow(() -> notFound("Federation", id)); }
    private Discipline discipline(Long id) { return disciplineRepository.findById(id).orElseThrow(() -> notFound("Discipline", id)); }
    private Championship championship(Long id) { return championshipRepository.findById(id).orElseThrow(() -> notFound("Championship", id)); }
    private Season season(Long id) { return seasonRepository.findById(id).orElseThrow(() -> notFound("Season", id)); }
    private CompetitionClass competitionClass(Long id) { return competitionClassRepository.findById(id).orElseThrow(() -> notFound("Competition class", id)); }
    private ChampionshipRound championshipRound(Long id) { return championshipRoundRepository.findById(id).orElseThrow(() -> notFound("Championship round", id)); }
    private RaceSession raceSession(Long id) { return raceSessionRepository.findById(id).orElseThrow(() -> notFound("Race session", id)); }
    private ResponseStatusException notFound(String type, Long id) { return new ResponseStatusException(HttpStatus.NOT_FOUND, type + " " + id + " was not found"); }

    private FederationResponse federationResponse(Federation value) { return new FederationResponse(value.getId(), value.getName(), value.getCountryCode()); }
    private DisciplineResponse disciplineResponse(Discipline value) { return new DisciplineResponse(value.getId(), value.getCode(), value.getName()); }
    private ChampionshipResponse championshipResponse(Championship value) { return new ChampionshipResponse(value.getId(), value.getFederation().getId(), value.getDiscipline().getId(), value.getName(), value.getShortName()); }
    private SeasonResponse seasonResponse(Season value) { return new SeasonResponse(value.getId(), value.getChampionship().getId(), value.getYear(), value.getName(), value.isActive(), value.getScoringSystem() == null ? null : value.getScoringSystem().getId()); }
    private CompetitionClassResponse competitionClassResponse(CompetitionClass value) { return new CompetitionClassResponse(value.getId(), value.getCode()); }
    private ChampionshipRoundResponse championshipRoundResponse(ChampionshipRound value) { return new ChampionshipRoundResponse(value.getId(), value.getSeason().getId(), roundSeasonParticipationRepository.findByRoundId(value.getId()).stream().map(participation -> participation.getSeason().getId()).filter(id -> !id.equals(value.getSeason().getId())).toList(), value.getHostFederation() == null ? null : value.getHostFederation().getId(), value.getRoundNumber(), value.getName(), value.getStartsOn(), value.getEndsOn(), value.getStatus()); }
    private RaceSessionResponse raceSessionResponse(RaceSession value) { return new RaceSessionResponse(value.getId(), value.getRound().getId(), value.getName(), value.getType(), value.getSessionOrder(), value.getScheduledAt()); }
}
