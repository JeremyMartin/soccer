package fr.ewaux.backend.model.request.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.ewaux.commons.utilities.model.AbstractLongNamingModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerRequest extends AbstractLongNamingModel {

}
