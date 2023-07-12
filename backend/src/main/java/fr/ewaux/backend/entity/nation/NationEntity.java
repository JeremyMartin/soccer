package fr.ewaux.backend.entity.nation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.entity.club.ClubEntity;
import fr.ewaux.commons.entity.AbstractLongNamingTranslatableEntity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@JsonInclude(value = Include.NON_NULL)
@Entity(name = "nation")
@Table(name = "nation")
public class NationEntity extends AbstractLongNamingTranslatableEntity {

	@OneToMany(mappedBy = "nation")
	List<ClubEntity> clubs;

	@Override
	public String toString() {
		return "NationEntity: { " + super.toString() + ", clubs:" + this.clubs + " }";
	}
}
