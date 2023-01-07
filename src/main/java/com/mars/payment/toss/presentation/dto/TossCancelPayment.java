package com.mars.payment.toss.presentation.dto;

import lombok.*;
import javax.validation.constraints.NotNull;

// https://docs.tosspayments.com/reference#%EA%B2%B0%EC%A0%9C-%EC%B7%A8%EC%86%8C
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossCancelPayment {
  @NotNull
  private String cancelReason;
  private Long cancelAmount;
}
