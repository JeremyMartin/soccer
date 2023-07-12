package fr.ewaux.backend.model.response.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.model.response.step.StepResponse;
import fr.ewaux.backend.model.response.team.TeamResponse;
import fr.ewaux.commons.utilities.model.AbstractLongModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonInclude(value = Include.NON_NULL)
public class MatchResponse extends AbstractLongModel {

	TeamResponse home;
	TeamResponse away;
	StepResponse step;
	Integer homeGoals;
	Integer awayGoals;
	boolean penalty;
	Integer homePenalty;
	Integer awayPenalty;
	Integer homeYellowCard;
	Integer homeRedCard;
	Integer awayYellowCard;
	Integer awayRedCard;
	boolean played;
}
