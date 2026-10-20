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
@Table(name = "competition_classes")
@Getter
@Setter
@NoArgsConstructor
public class CompetitionClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "archive_key", unique = true, length = 160)
    private String archiveKey;

    @Column(name = "identity_notes", columnDefinition = "TEXT")
    private String identityNotes;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(length = 150)
    private String name;

    @Column(nullable = false)
    private Integer displayOrder = 0;
}
