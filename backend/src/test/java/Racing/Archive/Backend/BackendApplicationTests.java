package Racing.Archive.Backend;

import org.junit.jupiter.api.Test;
import Racing.Archive.Backend.publicapi.PublicDashboardController;
import Racing.Archive.Backend.publicapi.PublicSourceController;
import Racing.Archive.Backend.domain.SourceDocument;
import Racing.Archive.Backend.repository.*;
import Racing.Archive.Backend.security.SecurityConfiguration;
import Racing.Archive.Backend.sources.SourceFileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest({ PublicDashboardController.class, PublicSourceController.class })
@Import(SecurityConfiguration.class)
class BackendApplicationTests {
    @Autowired MockMvc mvc;
    @MockitoBean SeasonRepository seasons;
    @MockitoBean ChampionshipRoundRepository rounds;
    @MockitoBean RaceSessionRepository sessions;
    @MockitoBean RoundSeasonParticipationRepository participations;
    @MockitoBean SessionResultRepository results;
    @MockitoBean ScoringSystemPositionRepository points;
    @MockitoBean DriverRepository drivers;
    @MockitoBean SourceDocumentRepository documents;
    @MockitoBean SourceFileService files;

    @Test
    void publicReadsWorkWithoutAnAdministratorOrDatabase() throws Exception {
        mvc.perform(get("/public/dashboard")).andExpect(status().isOk()).andExpect(content().json("[]"));
        mvc.perform(head("/public/dashboard")).andExpect(status().isOk());
    }

    @Test
    void legacyAdministrationAndLoginAreUnavailable() throws Exception {
        mvc.perform(get("/admin/drivers")).andExpect(status().isForbidden());
        mvc.perform(get("/login")).andExpect(status().isForbidden());
        mvc.perform(get("/csrf")).andExpect(status().isForbidden());
    }

    @Test
    void runtimeWritesAreDenied() throws Exception {
        mvc.perform(post("/admin/sessions/1/results/import")).andExpect(status().isForbidden());
        mvc.perform(post("/admin/pdf-conversions/session-results")).andExpect(status().isForbidden());
        mvc.perform(put("/admin/drivers/1")).andExpect(status().isForbidden());
        mvc.perform(delete("/admin/drivers/1")).andExpect(status().isForbidden());
        mvc.perform(post("/public/dashboard")).andExpect(status().isForbidden());
        mvc.perform(patch("/public/drivers/1")).andExpect(status().isForbidden());
    }

    @Test
    void registeredSourcesArePublicAttachmentsAndDoNotExposeServerPaths() throws Exception {
        SourceDocument document = new SourceDocument();
        document.setId(7L);
        document.setTitle("Official results");
        document.setFilePath("2026/reviewed-event/files/results.pdf");
        document.setMediaType("application/pdf");
        document.setSha256("a".repeat(64));
        when(documents.findByRoundIdOrderByIdAsc(1L)).thenReturn(java.util.List.of(document));
        when(documents.findById(7L)).thenReturn(java.util.Optional.of(document));
        when(files.file(document)).thenReturn(new ByteArrayResource("Original document".getBytes()) {
            @Override public String getFilename() { return "results.pdf"; }
        });
        mvc.perform(get("/public/rounds/1/sources"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Official results"))
                .andExpect(jsonPath("$[0].downloadUrl").value("/public/sources/7/download"))
                .andExpect(jsonPath("$[0].filePath").doesNotExist());
        mvc.perform(get("/public/sources/7/download"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(header().string("Content-Disposition", org.hamcrest.Matchers.containsString("attachment")))
                .andExpect(content().string("Original document"));
        mvc.perform(get("/public/sources/999/download")).andExpect(status().isNotFound());
    }
}
