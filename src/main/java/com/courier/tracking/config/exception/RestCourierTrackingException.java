package com.courier.tracking.config.exception;

public class RestCourierTrackingException extends RuntimeException {

	private Integer code;

	public RestCourierTrackingException(String message) {
		super(message);
	}

	public RestCourierTrackingException(String message, int code) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
