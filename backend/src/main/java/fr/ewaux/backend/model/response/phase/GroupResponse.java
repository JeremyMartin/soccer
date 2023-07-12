package fr.ewaux.backend.model.response.phase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.model.response.match.MatchResponse;
import fr.ewaux.backend.model.response.ranking.RankingResponse;
import fr.ewaux.backend.model.response.team.TeamResponse;
import fr.ewaux.commons.utilities.model.AbstractLongNamingModel;
import java.util.Set;
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
public class GroupResponse extends AbstractLongNamingModel {

	Set<TeamResponse> teams;
	Set<MatchResponse> matchs;
	Set<RankingResponse> rankings;
}
