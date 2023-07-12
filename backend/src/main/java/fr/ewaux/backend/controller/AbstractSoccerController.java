package fr.ewaux.backend.controller;

import fr.ewaux.commons.utilities.model.response.exception.ExceptionResponse;
import fr.ewaux.commons.utilities.utils.MessageHelper;
import java.time.LocalDateTime;

public abstract class AbstractSoccerController {

	protected ExceptionResponse buildExceptionResponse(final String message, final String cause) {
		return this.buildExceptionResponse(message, cause, null);
	}

	protected ExceptionResponse buildExceptionResponse(final String message, final String cause, final String path) {
		return ExceptionResponse.builder()
			.error(MessageHelper.ERROR)
			.message(message)
			.cause(cause)
			.path(path)
			.timestamp(LocalDateTime.now())
			.build();
	}
}
