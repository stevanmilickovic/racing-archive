package Racing.Archive.Backend.admin;

import Racing.Archive.Backend.domain.PublicationStatus;
import Racing.Archive.Backend.domain.RoundStatus;
import Racing.Archive.Backend.domain.SessionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

record FederationRequest(@NotBlank String name, String countryCode) {
}

record FederationResponse(Long id, String name, String countryCode) {
}

record DisciplineRequest(@NotBlank String code, @NotBlank String name) {
}

record DisciplineResponse(Long id, String code, String name) {
}

record ChampionshipRequest(@NotNull Long federationId, @NotNull Long disciplineId, @NotBlank String name, String shortName) {
}

record ChampionshipResponse(Long id, Long federationId, Long disciplineId, String name, String shortName) {
}

record SeasonRequest(@NotNull Long championshipId, @NotNull @Min(1900) @Max(3000) Short year, String name, boolean active, Long scoringSystemId) {
}

record SeasonResponse(Long id, Long championshipId, Short year, String name, boolean active, Long scoringSystemId) {
}

record CompetitionClassRequest(@NotBlank String code) {
}

record CompetitionClassResponse(Long id, String code) {
}

record ChampionshipRoundRequest(
        @NotNull Long seasonId,
        Long hostFederationId,
        @NotNull @Min(1) Integer roundNumber,
        @NotBlank String name,
        LocalDate startsOn,
        LocalDate endsOn,
        @NotNull RoundStatus status) {
}

record ChampionshipRoundResponse(
        Long id,
        Long seasonId,
        Long hostFederationId,
        Integer roundNumber,
        String name,
        LocalDate startsOn,
        LocalDate endsOn,
        RoundStatus status) {
}

record RaceSessionRequest(
        @NotNull Long roundId,
        @NotBlank String name,
        @NotNull SessionType type,
        @NotNull @Min(1) Integer sessionOrder,
        LocalDateTime scheduledAt) {
}

record RaceSessionResponse(Long id, Long roundId, String name, SessionType type, Integer sessionOrder, LocalDateTime scheduledAt) {
}
