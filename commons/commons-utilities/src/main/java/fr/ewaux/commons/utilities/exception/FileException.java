package fr.ewaux.commons.utilities.exception;

import lombok.Getter;

@Getter
public class FileException extends RuntimeException {

	Object source;
	Object destination;

	public FileException(final String message,
		final Object source) {
		super(message);
		this.source = source;
	}

	public FileException(final String message,
		final Object source,
		final Object destination) {
		super(message);
		this.source = source;
		this.destination = destination;
	}

}
