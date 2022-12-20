package com.mars.order.constant;

import com.mars.common.exception.vo.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum OrderExceptionInfo {

  INVALID_ORDER_AMOUNT("주문 금액은 0보다 커야 합니다", HttpStatus.BAD_REQUEST),
  INVALID_PRODUCT_LIST_SIZE("적어도 한 개 이상의 상품이 담겨야 합니다.", HttpStatus.BAD_REQUEST);

  private final String message;
  private final HttpStatus status;

  OrderExceptionInfo(String message, HttpStatus status) {
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
