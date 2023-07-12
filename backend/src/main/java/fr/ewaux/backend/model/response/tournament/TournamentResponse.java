package fr.ewaux.backend.model.response.tournament;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.model.response.phase.ConsolingResponse;
import fr.ewaux.backend.model.response.phase.GroupResponse;
import fr.ewaux.backend.model.response.phase.MainResponse;
import fr.ewaux.backend.model.response.step.StepResponse;
import fr.ewaux.backend.model.response.team.TeamResponse;
import fr.ewaux.commons.utilities.model.AbstractLongNamingModel;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder(toBuilder = true)
@ToString
@JsonInclude(value = Include.NON_NULL)
public class TournamentResponse extends AbstractLongNamingModel {

	@JsonFormat(shape = Shape.STRING)
	OffsetDateTime date;
	Integer matchType;
	Integer nbGroup;
	Set<TeamResponse> teams;
	Set<GroupResponse> groups;
	MainResponse main;
	ConsolingResponse consoling;
	StepResponse step;
}
