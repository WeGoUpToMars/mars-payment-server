package com.mars.payment.toss.presentation.dto;

import lombok.*;

// https://docs.tosspayments.com/reference#%EA%B2%B0%EC%A0%9C-%EC%8A%B9%EC%9D%B8
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TossConfirmPayment {
  private String paymentKey;
  private String orderId;
  private Long amount;
}
