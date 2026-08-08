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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "points_schemes", uniqueConstraints = @UniqueConstraint(name = "uk_points_scheme_class", columnNames = "competition_class_id"))
@Getter
@Setter
@NoArgsConstructor
public class PointsScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competition_class_id", nullable = false)
    private CompetitionClass competitionClass;

    @Column(nullable = false)
    private Integer minimumCompetitorsForFullPoints = 1;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal reducedPointsFactor = BigDecimal.ONE;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.TINYINT)
    private boolean awardFullPointsForForeignHost;
}
