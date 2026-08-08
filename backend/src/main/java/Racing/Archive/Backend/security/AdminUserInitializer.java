package Racing.Archive.Backend.security;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Component
public class AdminUserInitializer implements ApplicationRunner {

    private final AppUserRepository appUserRepository;
    private final AdminUserProperties adminUserProperties;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(
            AppUserRepository appUserRepository,
            AdminUserProperties adminUserProperties,
            PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.adminUserProperties = adminUserProperties;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (appUserRepository.existsByRole(UserRole.ADMIN)) {
            return;
        }

        if (!StringUtils.hasText(adminUserProperties.username()) || !StringUtils.hasText(adminUserProperties.password())) {
            throw new IllegalStateException("RACING_ARCHIVE_ADMIN_USERNAME and RACING_ARCHIVE_ADMIN_PASSWORD must be set before the first startup");
        }

        AppUser adminUser = new AppUser();
        adminUser.setUsername(adminUserProperties.username());
        adminUser.setPasswordHash(passwordEncoder.encode(adminUserProperties.password()));
        adminUser.setRole(UserRole.ADMIN);
        adminUser.setEnabled(true);
        appUserRepository.save(adminUser);
    }
}
