package com.mars.payment.kakao.domain.constant;

import com.mars.common.exception.vo.CustomException;
import org.springframework.http.HttpStatus;

public enum KakaoExceptionInfo {

  CANNOT_PREPARE_KAKAO_PAY("카카오 결제 준비 과정에서 문제가 발생했습니다.", HttpStatus.BAD_REQUEST),
  CANNOT_APPROVE_KAKAO_PAY("카카오 결제 승인 과정에서 문제가 발생했습니다.", HttpStatus.BAD_REQUEST),
  NO_VALID_ORDER_EXISTS("유효한 주문이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
  NO_VALID_PAYMENT_INFO("실제 결제 정보와 주문 내역 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
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
