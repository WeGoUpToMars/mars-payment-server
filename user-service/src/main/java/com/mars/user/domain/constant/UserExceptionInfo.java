package com.mars.user.domain.constant;

import com.mars.core.exception.vo.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserExceptionInfo {

  NOT_VALID_USER_NAME("한글, 영문 대,소문자로 구성된 1 ~ 10자로 구성되어야 합니다.", HttpStatus.BAD_REQUEST),
  NOT_VALID_ACCOUNT_ID("영문 소문자와 숫자로 구성된 5자 ~ 20자여야 합니다. (영문자 필수 입력)", HttpStatus.BAD_REQUEST),
  NOT_VALID_PASSWORD("영문 대,소문자, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자로 구성되어야 합니다.", HttpStatus.BAD_REQUEST);

  private final String message;
  private final HttpStatus status;

  UserExceptionInfo(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
  }

  public CustomException exception() {
    return new CustomException(this.message, this.status);
  }

  public CustomException exception(Throwable cause) {
    return new CustomException(this.message, this.status, cause);
  }
}
