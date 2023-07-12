package fr.ewaux.backend.model.response.ranking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.model.response.team.TeamResponse;
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
@EqualsAndHashCode()
@Builder(toBuilder = true)
@ToString
@JsonInclude(value = Include.NON_NULL)
public class RankingResponse {

	int rank;
	TeamResponse team;
	int play;
	int win;
	int draw;
	int lose;
	int diff;
	int yellowCard;
	int redCard;
	int points;
}
