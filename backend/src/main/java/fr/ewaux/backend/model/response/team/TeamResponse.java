package fr.ewaux.backend.model.response.team;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.model.response.club.ClubResponse;
import fr.ewaux.backend.model.response.nation.NationResponse;
import fr.ewaux.backend.model.response.player.PlayerResponse;
import fr.ewaux.commons.utilities.model.AbstractLongModel;
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
@ToString(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
public class TeamResponse extends AbstractLongModel {

	PlayerResponse player;
	ClubResponse club;
	NationResponse nation;
}
