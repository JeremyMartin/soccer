package fr.ewaux.commons.utilities.exception;

import lombok.Getter;

@Getter
public class ExternalApiException extends RuntimeException {

	String url;
	int status;

	public ExternalApiException(final String message, final String url, final int status) {
		super(message);
		this.url = url;
		this.status = status;
	}

	public ExternalApiException(final String message, final int status) {
		super(message);
		this.status = status;
	}
}
