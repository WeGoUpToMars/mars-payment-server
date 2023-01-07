package com.mars.common.exception.vo;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

  private final String message;
  private final HttpStatus status;

  public CustomException(String message, HttpStatus status) {
    super(message);
    this.status = status;
    this.message = message;
  }

  public CustomException(String message, HttpStatus status, Throwable cause) {
    super(message, cause);
    this.status = status;
    this.message = message;
  }

  public static CustomException of(String message, HttpStatus status) {
    return new CustomException(message, status);
  }

  public static CustomException of(String message, HttpStatus status, Throwable cause) {
    return new CustomException(message, status, cause);
  }
}
