package Racing.Archive.Backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "source_documents")
@Getter
@Setter
@NoArgsConstructor
public class SourceDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String sourceKey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "round_id", nullable = false)
    private ChampionshipRound round;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false, unique = true, length = 500)
    private String filePath;

    @Column(nullable = false, length = 100)
    private String mediaType;

    @Column(nullable = false, length = 64)
    private String sha256;

    @Column(length = 1000)
    private String originalUrl;

    @Column(columnDefinition = "TEXT")
    private String notes;
}
