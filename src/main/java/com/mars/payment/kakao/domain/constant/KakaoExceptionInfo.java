package com.mars.payment.kakao.domain.constant;

import com.mars.common.exception.vo.CustomException;
import org.springframework.http.HttpStatus;

public enum KakaoExceptionInfo {

  CANNOT_PREPARE_KAKAO_PAY("카카오 결제 준비 과정에서 문제가 발생했습니다.", HttpStatus.BAD_REQUEST),
  ;

  private final String message;
  private final HttpStatus status;

  KakaoExceptionInfo(String message, HttpStatus status) {
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
