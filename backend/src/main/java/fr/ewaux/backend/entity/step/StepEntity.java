package fr.ewaux.backend.entity.step;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.model.response.step.StepResponse;
import fr.ewaux.commons.entity.AbstractLongNamingTranslatableEntity;
import java.util.Objects;
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
@Entity(name = "step")
@Table(name = "step")
public class StepEntity extends AbstractLongNamingTranslatableEntity {

	@Override
	public String toString() {
		return "StepEntity:{ " + super.toString() + " }";
	}
}
