package Racing.Archive.Backend.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "racing-archive.admin")
public record AdminUserProperties(String username, String password) {
}
