package fr.ewaux.backend.model.request.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class StepRequest extends AbstractLongNamingTranslatableModel {

}
