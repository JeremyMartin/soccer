package fr.ewaux.commons.web.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import fr.ewaux.commons.utilities.exception.ExternalApiException;
import fr.ewaux.commons.utilities.exception.FileException;
import fr.ewaux.commons.utilities.model.response.exception.ExceptionResponse;
import fr.ewaux.commons.utilities.model.response.exception.ExpectedResponse;
import fr.ewaux.commons.utilities.utils.MessageHelper;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class ErrorController extends ResponseEntityExceptionHandler {

	protected String resolvePathFromHttpServletRequest(final WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURI();
	}

	protected ResponseEntity<Object> buildResponseEntity(final Exception ex, final String message, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		String path = this.resolvePathFromHttpServletRequest(request);
		logger.error(path + " => " + ex.getMessage(), ex);
		ExceptionResponse body = ExceptionResponse.builder().error(MessageHelper.ERROR).message(message).cause(ex.getMessage()).path(path)
			.timestamp(LocalDateTime.now()).build();
		return new ResponseEntity<>(body, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
		final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		String path = this.resolvePathFromHttpServletRequest(request);
		logger.error(path + " => " + ex.getMessage(), ex);
		ExceptionResponse body = ExceptionResponse.builder().error(MessageHelper.ERROR)
			.message(MessageHelper.ERROR_REQUEST_METHOD_NOT_SUPPORTED).path(path).expected(ex.getMethod())
			.found(ex.getSupportedMethods()).build();
		return new ResponseEntity<>(body, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_MEDIA_TYPE_NOT_SUPPORTED, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(final HttpMediaTypeNotAcceptableException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_MEDIA_TYPE_NOT_ACCEPTABLE, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(final MissingPathVariableException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_MISSING_PATH_VARIABLE, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex,
		final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_MISSING_SERVLET_REQUEST_PARAMETER, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(final ServletRequestBindingException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_SERVLET_BINDING, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(final ConversionNotSupportedException ex, final HttpHeaders headers,
		final HttpStatus status,
		final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_CONVERSION_NOT_SUPPORTED, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status,
		final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_TYPE_MISMATCH, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		String path = this.resolvePathFromHttpServletRequest(request);
		logger.error(path + " => " + ex.getMessage(), ex);
		ExceptionResponse body;
		if (ex.getCause() instanceof InvalidFormatException) {
			InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
			List<String> found = Arrays.stream(invalidFormatException.getTargetType().getFields()).map(Field::getName)
				.collect(Collectors.toList());
			Object expected = invalidFormatException.getValue();
			body = ExceptionResponse.builder().error(MessageHelper.ERROR).message(MessageHelper.ERROR_MESSAGE_NOT_READABLE).path(path)
				.expected(expected).found(found)
				.timestamp(LocalDateTime.now()).build();
		} else {
			body = ExceptionResponse.builder().error(MessageHelper.ERROR).message(MessageHelper.ERROR_MESSAGE_NOT_READABLE).path(path)
				.timestamp(LocalDateTime.now()).build();
		}
		return new ResponseEntity<>(body, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(final HttpMessageNotWritableException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_MESSAGE_NOT_WRITABLE, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		String path = this.resolvePathFromHttpServletRequest(request);
		logger.error(path + " => " + ex.getMessage(), ex);
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		List<Object> expected = new ArrayList<>();
		for (FieldError fieldError : fieldErrors) {
			expected.add(
				ExpectedResponse.builder().field(fieldError.getField()).value(fieldError.getRejectedValue()).message(fieldError.getDefaultMessage())
					.build());
		}
		ExceptionResponse body = ExceptionResponse.builder().error(MessageHelper.ERROR).message(MessageHelper.ERROR_ARGUMENT_NOT_VALID)
			.path(path).expected(expected)
			.timestamp(LocalDateTime.now()).build();
		return new ResponseEntity<>(body, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_MISSING_SERVLET_REQUEST_PART, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status,
		final WebRequest request) {
		// TODO implement ex.getBindingResult().
		return this.buildResponseEntity(ex, MessageHelper.ERROR_BINDING, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		String path = this.resolvePathFromHttpServletRequest(request);
		logger.error(path + " => " + ex.getMessage(), ex);
		ExceptionResponse body = ExceptionResponse.builder().error(MessageHelper.ERROR).message(MessageHelper.ERROR_URL_NOT_FOUND)
			.path(path).timestamp(LocalDateTime.now()).build();
		return new ResponseEntity<>(body, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(final AsyncRequestTimeoutException ex, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_ASYNC_REQUEST_TIMEOUT, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final Object body, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		return this.buildResponseEntity(ex, MessageHelper.ERROR_INTERNAL_EXCEPTION, headers, status, request);
	}

	@ExceptionHandler(value = {FileException.class})
	protected ResponseEntity<Object> handleFileException(final FileException ex, final WebRequest request) {
		String path = this.resolvePathFromHttpServletRequest(request);
		logger.error(path + " => " + ex.getMessage(), ex);
		List<ExpectedResponse> expectedResponses = new ArrayList<>();
		if (ex.getSource() != null) {
			expectedResponses.add(ExpectedResponse.builder().field("source").value(ex.getSource()).build());
		}
		if (ex.getDestination() != null) {
			expectedResponses.add(ExpectedResponse.builder().field("destination").value(ex.getDestination()).build());
		}
		ExceptionResponse body = ExceptionResponse.builder().error(MessageHelper.ERROR).message(MessageHelper.ERROR_FILE).cause(ex.getMessage())
			.expected(expectedResponses).path(path).timestamp(LocalDateTime.now()).build();
		return new ResponseEntity<>(body, null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = {ExternalApiException.class})
	protected ResponseEntity<Object> handleExternalApiException(final ExternalApiException ex, final WebRequest request) {
		String path = this.resolvePathFromHttpServletRequest(request);
		logger.error(path + " => " + ex.getMessage(), ex);
		ExceptionResponse body = ExceptionResponse.builder().message(MessageHelper.ERROR).message(MessageHelper.ERROR_EXTERNAL_WEB_API)
			.cause(ex.getMessage() + " , url:" + ex.getUrl() + ", status:" + ex.getStatus()).path(path).timestamp(LocalDateTime.now()).build();
		return new ResponseEntity<>(body, null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
