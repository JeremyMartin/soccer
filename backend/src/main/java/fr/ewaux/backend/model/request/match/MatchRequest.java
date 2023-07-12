package fr.ewaux.backend.model.request.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.ewaux.backend.model.request.step.StepRequest;
import fr.ewaux.backend.model.request.team.TeamRequest;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchRequest extends AbstractLongModel {

	TeamRequest home;
	TeamRequest away;
	StepRequest step;
	Integer homeGoals;
	Integer awayGoals;
	boolean penalty;
	Integer homePenalty;
	Integer awayPenalty;
	Integer homeYellowCard;
	Integer homeRedCard;
	Integer awayYellowCard;
	Integer awayRedCard;
}
