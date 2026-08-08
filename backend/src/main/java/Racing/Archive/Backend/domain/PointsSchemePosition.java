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
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "points_scheme_positions", uniqueConstraints = @UniqueConstraint(name = "uk_points_scheme_position", columnNames = {"points_scheme_id", "position"}))
@Getter
@Setter
@NoArgsConstructor
public class PointsSchemePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "points_scheme_id", nullable = false)
    private PointsScheme pointsScheme;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal points;
}
