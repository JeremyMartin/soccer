package fr.ewaux.backend.model.request.club;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.ewaux.backend.model.request.nation.NationRequest;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubRequest extends AbstractLongNamingModel {

	String shortName;
	NationRequest nation;
}
