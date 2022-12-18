package fr.ewaux.commons.utilities.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(Include.NON_NULL)
public class ExceptionResponse {
	@JsonFormat(shape = Shape.STRING)
	LocalDateTime timestamp;
	String error;
	String message;
	String path;
	Object expected;
	Object found;
	String cause;
}

