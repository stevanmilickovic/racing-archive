package Racing.Archive.Backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scoring_system_positions", uniqueConstraints = @UniqueConstraint(columnNames = {"scoring_system_id", "position"}))
@Getter @Setter @NoArgsConstructor
public class ScoringSystemPosition {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "scoring_system_id") private ScoringSystem scoringSystem;
    private Integer position;
    private BigDecimal points;
}
