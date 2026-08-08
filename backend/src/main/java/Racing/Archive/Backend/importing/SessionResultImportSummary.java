package Racing.Archive.Backend.importing;

public record SessionResultImportSummary(
        int importedRows,
        int createdDrivers,
        int createdClubs,
        int createdVehicles,
        int createdEntries,
        int updatedSessionResults) {
}
