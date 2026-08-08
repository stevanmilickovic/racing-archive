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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "club_scoring_rules", uniqueConstraints = @UniqueConstraint(name = "uk_club_scoring_rule_class", columnNames = "competition_class_id"))
@Getter
@Setter
@NoArgsConstructor
public class ClubScoringRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competition_class_id", nullable = false)
    private CompetitionClass competitionClass;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.TINYINT)
    private boolean countAllResults;

    private Integer resultsToCount;
}
