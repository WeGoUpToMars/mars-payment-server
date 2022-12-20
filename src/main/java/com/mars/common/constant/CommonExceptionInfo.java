package com.mars.common.constant;

import com.mars.common.exception.vo.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonExceptionInfo {

  INVALID_INPUT_VALUE("올바르지 않은 입력값입니다.", HttpStatus.BAD_REQUEST),
  INVALID_TYPE_VALUE("올바르지 않은 타입이 입력되었습니다.", HttpStatus.BAD_REQUEST),
  METHOD_NOT_ALLOWED("해당 메서드를 사용할 수 없습니다.", HttpStatus.BAD_REQUEST),
  UNKNOWN_EXCEPTION("알 수 없는 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);

  private final String message;
  private final HttpStatus status;

  CommonExceptionInfo(String message, HttpStatus status) {
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
