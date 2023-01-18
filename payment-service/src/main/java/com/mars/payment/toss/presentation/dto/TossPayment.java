package com.mars.payment.toss.presentation.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
  private List<Cancel> cancels;

  public List<Cancel> getCancels() {
    return Objects.isNull(cancels) ? Collections.emptyList() : this.cancels;
  }

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

  @Getter
  @ToString
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Builder
  public static class Cancel {
    private Long cancelAmount;
    private String cancelReason;
    private Long taxFreeAmount;
    private Integer taxExemptionAmount;
    private Long refundableAmount;
    private Long easyPayDiscountAmount;
    private String canceledAt;
    private String transactionKey;
  }
}
