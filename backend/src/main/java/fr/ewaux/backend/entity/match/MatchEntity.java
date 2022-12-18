package fr.ewaux.backend.entity.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.step.StepEntity;
import fr.ewaux.backend.entity.team.TeamEntity;
import fr.ewaux.commons.entity.AbstractLongEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@ToString
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "match")
@Table(name = "match")
public class MatchEntity extends AbstractLongEntity {
	@OneToOne
	@JoinColumn(name = "home_id", referencedColumnName = "id")
	TeamEntity home;
	@OneToOne
	@JoinColumn(name = "away_id", referencedColumnName = "id")
	TeamEntity away;
	@OneToOne
	@JoinColumn(name = "step_id", referencedColumnName = "id")
	StepEntity step;
	@Column(name = "home_goals", columnDefinition = "int")
	Integer homeGoals;
	@Column(name = "away_goals", columnDefinition = "int")
	Integer awayGoals;
	@Column(name = "penalty", columnDefinition = "boolean")
	boolean penalty;
	@Column(name = "home_penalty", columnDefinition = "int")
	Integer homePenalty;
	@Column(name = "away_penalty", columnDefinition = "int")
	Integer awayPenalty;
}
