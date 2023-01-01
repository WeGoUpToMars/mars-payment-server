package com.mars.payment.kakao.dto;

import com.mars.payment.common.dto.InquiryInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoInquiryInfo {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Request implements InquiryInfo.Request {

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response implements InquiryInfo.Response {

  }
}
