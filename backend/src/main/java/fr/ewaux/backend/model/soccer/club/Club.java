package fr.ewaux.backend.model.soccer.club;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.model.soccer.nation.Nation;
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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonInclude(value = Include.NON_NULL)
public class Club extends AbstractLongNamingModel {

	String shortName;
	Nation nation;
}
