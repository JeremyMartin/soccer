package fr.ewaux.backend.model.request.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.ewaux.backend.model.request.club.ClubRequest;
import fr.ewaux.backend.model.request.nation.NationRequest;
import fr.ewaux.backend.model.request.player.PlayerRequest;
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
public class TeamRequest extends AbstractLongModel {

	PlayerRequest player;
	ClubRequest club;
	NationRequest nation;
}
