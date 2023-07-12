package fr.ewaux.backend.entity.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.step.StepEntity;
import fr.ewaux.backend.entity.team.TeamEntity;
import fr.ewaux.commons.entity.AbstractLongEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "match")
@Table(name = "match")
public class MatchEntity extends AbstractLongEntity {

	@OneToOne
	@JoinColumn(name = "home_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_match_team_home_id"))
	TeamEntity home;
	@OneToOne
	@JoinColumn(name = "away_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_match_team_away_id"))
	TeamEntity away;
	@OneToOne
	@JoinColumn(name = "step_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_match_step_id"))
	StepEntity step;
	@Column(name = "home_goals", columnDefinition = "integer")
	Integer homeGoals;
	@Column(name = "away_goals", columnDefinition = "integer")
	Integer awayGoals;
	@Column(name = "penalty", columnDefinition = "boolean default false")
	boolean penalty;
	@Column(name = "home_penalty", columnDefinition = "integer")
	Integer homePenalty;
	@Column(name = "away_penalty", columnDefinition = "integer")
	Integer awayPenalty;
	@Column(name = "home_yellow_card", columnDefinition = "integer")
	Integer homeYellowCard;
	@Column(name = "home_red_card", columnDefinition = "integer")
	Integer homeRedCard;
	@Column(name = "away_yellow_card", columnDefinition = "integer")
	Integer awayYellowCard;
	@Column(name = "away_red_card", columnDefinition = "integer")
	Integer awayRedCard;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("MatchEntity: {").append(super.toString()).append(", ");
		if (Objects.nonNull(this.step)){
			sb.append(this.step);
		}else {
			sb.append("step: null");
		}
		sb.append(", ").append(this.home).append(this.away);
		return sb.toString();
	}
}
