package fr.ewaux.backend.model.response.step;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.commons.utilities.model.AbstractLongNamingTranslatableModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonInclude(value = Include.NON_NULL)
public class StepResponse extends AbstractLongNamingTranslatableModel {

}
