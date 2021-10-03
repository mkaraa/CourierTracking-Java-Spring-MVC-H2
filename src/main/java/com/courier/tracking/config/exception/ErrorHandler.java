package com.courier.tracking.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler (RestCourierTrackingException.class)
	public ResponseEntity<RestError> handleCourierIsSameLocationException(RestCourierTrackingException e, WebRequest webRequest) {
		RestError applicationError = new RestError();
		if(ObjectUtils.isEmpty(e.getCode())){
			applicationError.setErrorCode(400);
		} else {
			applicationError.setErrorCode(e.getCode());
		}
		applicationError.setErrorMessage(e.getMessage());

		return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
	}
}
