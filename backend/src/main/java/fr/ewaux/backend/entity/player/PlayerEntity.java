package fr.ewaux.backend.entity.player;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.commons.entity.AbstractLongNamingEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "player")
@Table(name = "player")
public class PlayerEntity extends AbstractLongNamingEntity {

	@Override
	public String toString() {
		return "PlayerEntity: { " + super.toString() + " }";
	}
}
