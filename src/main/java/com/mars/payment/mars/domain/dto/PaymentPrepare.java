package com.mars.payment.mars.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentPrepare {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Request {
    private String accountId;
    private String store; // 가맹점 이름
    private String itemName;
    private Integer quantity;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {
    private String tid;
    private String redirectAppUrl;
    private String redirectMobileUrl;
    private String redirectPcUrl;
    private String androidAppScheme;
    private String iosAppScheme;
  }
}
