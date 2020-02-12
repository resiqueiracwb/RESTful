package com.restfulldemo.exception;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.restfulldemo.controller.TransactionController;
import com.restfulldemo.model.APIError;

@ControllerAdvice(assignableTypes = TransactionController.class)
public class ControllersExceptionHandler {
	/**
	 * Method that handles with API exceptions
	 * 
	 * @author Renato Siqueira
	 * @since 11/02/2020
	 * 
	 * @param exception
	 * @param request
	 * 
	 * @return ResponseEntity<APIError>
	 */
	@ExceptionHandler({ InvalidJSONException.class, NotParsableContentException.class,
			TransactionNotFoundException.class })
	public final ResponseEntity<APIError> handleException(Exception exception, WebRequest request) {

		HttpHeaders headers = new HttpHeaders();

		if (exception instanceof TransactionNotFoundException) {
			HttpStatus status = HttpStatus.NOT_FOUND;
			TransactionNotFoundException unfe = (TransactionNotFoundException) exception;

			return handleTransactionNotFoundException(unfe, headers, status, request);

		} else if (exception instanceof InvalidJSONException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			InvalidJSONException cnae = (InvalidJSONException) exception;

			return handleInvalidJSONException(cnae, headers, status, request);

		} else if (exception instanceof NotParsableContentException) {
			HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
			NotParsableContentException cnae = (NotParsableContentException) exception;

			return handleNotParsableContentException(cnae, headers, status, request);

		} else {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return handleExceptionInternal(exception, null, headers, status, request);
		}
	}

	/**
	 * Method that customize the response for TransactionNotFoundException.
	 * 
	 * @author Renato Siqueira
	 * @since 11/02/2020
	 * 
	 * @param exception
	 * @param headers
	 * @param status
	 * @param request
	 * 
	 * @return ResponseEntity<APIError>
	 */
	protected ResponseEntity<APIError> handleTransactionNotFoundException(TransactionNotFoundException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = Collections.singletonList(exception.getMessage());
		return handleExceptionInternal(exception, new APIError(status, status.value(), exception.getMessage(), errors),
				headers, status, request);
	}

	/**
	 * Method that customize the response for InvalidJSONException.
	 * 
	 * @author Renato Siqueira
	 * @since 11/02/2020
	 * 
	 * @param exception
	 * @param headers
	 * @param status
	 * @param request
	 * 
	 * @return ResponseEntity<APIError>
	 */
	protected ResponseEntity<APIError> handleInvalidJSONException(InvalidJSONException exception, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		List<String> errors = Collections.singletonList(exception.getMessage());
		return handleExceptionInternal(exception, new APIError(status, status.value(), exception.getMessage(), errors),
				headers, status, request);
	}

	/**
	 * Method that customize the response for NotParsableContentException
	 * 
	 * @author Renato Siqueira
	 * @since 11/02/2020
	 * 
	 * @param exception
	 * @param headers
	 * @param status
	 * @param request
	 * 
	 * @return ResponseEntity<APIError>
	 */
	protected ResponseEntity<APIError> handleNotParsableContentException(NotParsableContentException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = Collections.singletonList(exception.getMessage());
		return handleExceptionInternal(exception, new APIError(status, status.value(), exception.getMessage(), errors),
				headers, status, request);
	}

	/**
	 * Method that customize the response body of all Exception types.
	 * 
	 * @author Renato Siqueira
	 * @since 11/02/2020
	 * 
	 * @param exception
	 * @param body
	 * @param headers
	 * @param status
	 * @param request
	 * 
	 * @return ResponseEntity<APIError>
	 */
	protected ResponseEntity<APIError> handleExceptionInternal(Exception exception, APIError body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
		}

		return new ResponseEntity<>(body, headers, status);
	}	
}
