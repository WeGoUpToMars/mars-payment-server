package com.mars.payment.toss.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TossPayConstant {
  private static final String host = "https://api.tosspayments.com";

  public static String getCreatePaymentUrl() {
    return host + "/v1/payments";
  }

  public static String getConfirmPaymentUrl() {
    return host + "/v1/payments/confirm";
  }

  public static String getCancelPaymentUrl(String paymentKey) {
    Objects.requireNonNull(paymentKey);
    return host + "/v1/payments/" + paymentKey + "/cancel";
  }
}
