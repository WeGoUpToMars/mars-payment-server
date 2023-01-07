package com.mars.payment.toss.presentation.dto;

import lombok.*;

// https://docs.tosspayments.com/reference#%EA%B2%B0%EC%A0%9C-%EC%B7%A8%EC%86%8C
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TossCancelPayment {
  private String cancelReason;
  private String cancelAmount;
}
