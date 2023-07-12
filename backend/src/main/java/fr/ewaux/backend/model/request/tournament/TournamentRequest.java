package fr.ewaux.backend.model.request.tournament;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.ewaux.backend.model.request.team.TeamRequest;
import fr.ewaux.commons.utilities.model.AbstractLongNamingModel;
import java.util.List;
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
public class TournamentRequest extends AbstractLongNamingModel {

	int matchType;
	int nbGroup;
	List<TeamRequest> teams;
}
