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
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "classification_sessions", uniqueConstraints = {
        @UniqueConstraint(name = "uk_classification_session", columnNames = {"classification_id", "session_id"}),
        @UniqueConstraint(name = "uk_classification_source_order", columnNames = {"classification_id", "source_order"})
})
@Getter
@Setter
@NoArgsConstructor
public class ClassificationSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "classification_id", nullable = false)
    private Classification classification;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private RaceSession session;

    @Column(name = "source_order", nullable = false)
    private Integer sourceOrder;
}
