package fr.ewaux.backend.model.response.player;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.commons.utilities.model.AbstractLongNamingModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@ToString(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
public class PlayerResponse extends AbstractLongNamingModel {

}
