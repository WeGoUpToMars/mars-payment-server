package com.mars.payment.toss.constant;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TossPayConstant {
  private static final String HOST = "https://api.tosspayments.com";

  public static String getCreatePaymentUrl() {
    return HOST + "/v1/payments";
  }

  public static String getConfirmPaymentUrl() {
    return HOST + "/v1/payments/confirm";
  }

  public static String getCancelPaymentUrl(String paymentKey) {
    Objects.requireNonNull(paymentKey);
    return HOST + "/v1/payments/" + paymentKey + "/cancel";
  }

  public static String getOrderInquiryUrl(String orderId) {
    Objects.requireNonNull(orderId);
    return HOST + "/v1/payments/orders/" + orderId;
  }

  public static String getPaymentInquiryUrl(String paymentKey) {
    Objects.requireNonNull(paymentKey);
    return HOST + "/v1/payments/" + paymentKey;
  }
}
