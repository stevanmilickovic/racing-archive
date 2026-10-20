package Racing.Archive.Backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tracks")
@Getter
@Setter
@NoArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "archive_key", nullable = false, unique = true, length = 160)
    private String archiveKey;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 200)
    private String location;

    @Column(name = "country_code", length = 2)
    private String countryCode;

    @Column(name = "identity_notes", columnDefinition = "TEXT")
    private String identityNotes;
}
