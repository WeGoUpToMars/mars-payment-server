package com.mars.payment.toss.presentation.dto;

import lombok.*;

// https://docs.tosspayments.com/reference#payment-%EA%B0%9D%EC%B2%B4
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TossPayment {
  private String version;
  private String paymentKey;
  private String type;
  private String orderId;
  private String orderName;
  private String mId;
  private String currency;
  private String method;
  private Long totalAmount;
  private Long balanceAmount;
  private Long suppliedAmount;
  private Long vat;
  private String status;
  private String requestedAt;
  private String approvedAt;
  private String lastTransactionKey;
  private Receipt receipt;
  private Checkout checkout;
  private Failure failure;

  @Getter
  @ToString
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Builder
  public static class Receipt {
    private String url;
  }

  @Getter
  @ToString
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Builder
  public static class Checkout {
    private String url;
  }

  @Getter
  @ToString
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Builder
  public static class Failure {
    private String code;
    private String message;
  }
}
