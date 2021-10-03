package com.courier.tracking.config.exception;

import lombok.Data;

@Data
public class RestError {

  private int errorCode;
  private String errorMessage;

}
