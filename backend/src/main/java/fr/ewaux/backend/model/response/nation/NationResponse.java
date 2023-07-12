package fr.ewaux.backend.model.response.nation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import fr.ewaux.backend.model.response.club.ClubResponse;
import fr.ewaux.commons.utilities.model.AbstractLongNamingTranslatableModel;
import java.util.Set;
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
@ToString(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
public class NationResponse extends AbstractLongNamingTranslatableModel {

	Set<ClubResponse> clubs;
}
