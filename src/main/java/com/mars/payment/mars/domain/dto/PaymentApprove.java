package com.mars.payment.mars.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentApprove {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Request {
    private String accountId;
    private String tid;
    private String pgToken;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {
    private String status; // approved, failed
  }
}
