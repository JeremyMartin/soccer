package fr.ewaux.commons.utilities.model.response.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ExpectedResponse {

	String field;
	Object value;
	@JsonInclude(value = Include.NON_NULL)
	String message;
}
