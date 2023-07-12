package fr.ewaux.backend.model.request.nation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.ewaux.backend.model.request.club.ClubRequest;
import fr.ewaux.commons.utilities.model.AbstractLongNamingTranslatableModel;
import java.util.List;
import java.util.Set;
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
public class NationRequest extends AbstractLongNamingTranslatableModel {

	List<ClubRequest> clubs;
}
