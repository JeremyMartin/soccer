package fr.ewaux.backend.entity.team;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.club.ClubEntity;
import fr.ewaux.backend.entity.nation.NationEntity;
import fr.ewaux.backend.entity.player.PlayerEntity;
import fr.ewaux.commons.entity.AbstractLongEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "team")
@Table(name = "team")
public class TeamEntity extends AbstractLongEntity {

	@OneToOne
	@JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_team_player_id"))
	PlayerEntity player;
	@OneToOne
	@JoinColumn(name = "club_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_team_club_id"))
	ClubEntity club;
	@OneToOne
	@JoinColumn(name = "nation_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_team_nation_id"))
	NationEntity nation;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("TeamEntity: {").append(super.toString()).append(", ").append(this.player);
		sb.append(", ");
		if (Objects.nonNull(this.club)){
			sb.append(this.club);
		}else {
			sb.append("club: null");
		}
		sb.append(", ");
		if (Objects.nonNull(this.nation)){
			sb.append(this.nation);
		}else {
			sb.append("nation: null");
		}
		sb.append(" }");
		return sb.toString();
	}
}
