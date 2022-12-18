package fr.ewaux.scrapping.model.external.club;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.ewaux.scrapping.model.external.common.Pagination;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubsResponse {

  Pagination pagination;
  Set<Club> items;
}
