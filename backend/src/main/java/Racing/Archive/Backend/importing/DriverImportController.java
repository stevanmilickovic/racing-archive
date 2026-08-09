package Racing.Archive.Backend.importing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/drivers")
@RequiredArgsConstructor
public class DriverImportController {

    private final DriverImportService driverImportService;

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    DriverImportSummary importDrivers(@RequestParam("file") MultipartFile file) {
        return driverImportService.importFile(file);
    }
}
