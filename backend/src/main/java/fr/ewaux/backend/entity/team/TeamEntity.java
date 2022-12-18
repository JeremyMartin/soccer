package fr.ewaux.backend.entity.team;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.club.ClubEntity;
import fr.ewaux.backend.entity.nation.NationEntity;
import fr.ewaux.backend.entity.player.PlayerEntity;
import fr.ewaux.commons.entity.AbstractLongEntity;
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
@Entity(name = "team")
@Table(name = "team")
public class TeamEntity extends AbstractLongEntity {

	@OneToOne
	@JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
	PlayerEntity player;
	@OneToOne
	@JoinColumn(name = "club_id", referencedColumnName = "id")
	ClubEntity club;
	@OneToOne
	@JoinColumn(name = "nation_id", referencedColumnName = "id")
	NationEntity nation;

}
