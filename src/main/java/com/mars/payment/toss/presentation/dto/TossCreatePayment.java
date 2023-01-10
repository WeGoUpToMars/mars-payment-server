package com.mars.payment.toss.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// https://docs.tosspayments.com/reference#%EA%B2%B0%EC%A0%9C-%EC%83%9D%EC%84%B1
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossCreatePayment {
  private String method;
  private Long amount;
  private String orderId;
  private String orderName;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private final String successUrl = "http://localhost:18189/mars/payments/toss/success";
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private final String failUrl = "http://localhost:18189/mars/payments/toss/fail";
}
