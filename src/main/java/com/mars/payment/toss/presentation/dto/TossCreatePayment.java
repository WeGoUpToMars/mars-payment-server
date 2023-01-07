package com.mars.payment.toss.presentation.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// https://docs.tosspayments.com/reference#%EA%B2%B0%EC%A0%9C-%EC%83%9D%EC%84%B1
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TossCreatePayment {
  private String method;
  private Long amount;
  private String orderId;
  private String orderName;
  private final String successUrl = "http://localhost:18189/mars/payments/toss/success";
  private final String failUrl = "http://localhost:18189/mars/payments/toss/fail";

  @Builder
  public TossCreatePayment(String method, Long amount, String orderId, String orderName) {
    this.method = method;
    this.amount = amount;
    this.orderId = orderId;
    this.orderName = orderName;
  }
}
