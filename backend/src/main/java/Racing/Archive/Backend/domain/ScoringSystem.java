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
@Table(name = "scoring_systems")
@Getter
@Setter
@NoArgsConstructor
public class ScoringSystem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true, length = 150) private String name;
    @Column(nullable = false) private Integer minimumCompetitorsForFullPoints;
    @Column(nullable = false) private Integer reducedPointsPercentage;
    @Column(nullable = false, columnDefinition = "TINYINT(1)") private boolean awardFullPointsForForeignHost;
    @Column(nullable = false) private Integer resultsToDrop;
    @Column(nullable = false, columnDefinition = "TINYINT(1)") private boolean disqualifiedResultEligibleForDrop;
}
