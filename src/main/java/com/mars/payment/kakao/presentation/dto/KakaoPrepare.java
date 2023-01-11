package com.mars.payment.kakao.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.common.util.MultiValueMapConverter;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoPrepare {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Request {
    private String cid; // 가맹점 코드
    @JsonProperty("partner_order_id")
    private String partnerOrderId; // 가맹점 주문 번호
    @JsonProperty("partner_user_id")
    private String partnerUserId;// 가맹점 회원 id
    @JsonProperty("item_name")
    private String itemName; // 상품명
    private Integer quantity; // 상품 수량
    @JsonProperty("total_amount")
    private Integer totalAmount; // 상품 총액
    @JsonProperty("tax_free_amount")
    private Integer taxFreeAmount; // 상품 비과세 금액
    @JsonProperty("approval_url")
    private String approvalUrl; // 결제 성공 시 redirect url
    @JsonProperty("cancel_url")
    private String cancelUrl; // 결제 취소 시 redirect url
    @JsonProperty("fail_url")
    private String failUrl; // 결제 실패 시 redirect url
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {
    private String tid; // 결제 고유 번호
    @JsonProperty("next_redirect_app_url")
    private String nextRedirectAppUrl; // 요청한 클라이언트가 모바일 앱일 경우 결제페이지 redirect url
    @JsonProperty("next_redirect_mobile_url")
    private String nextRedirectMobileUrl; // 요청한 클라이언트가 모바일 웹일 경우 결제페이지 redirect url
    @JsonProperty("next_redirect_pc_url")
    private String nextRedirectPcUrl; // 요청한 클라이언트가 PC 웹일 경우 결제 요청 메세지를 보내기 위한 사용자 정보 입력 화면 redirect url
    @JsonProperty("android_app_scheme")
    private String androidAppScheme; // 결제 화면으로 이동하는 Android 앱 스킴
    @JsonProperty("ios_app_scheme")
    private String iosAppScheme; // 결제 화면으로 이동하는 ios 앱 스킴
    @JsonProperty("created_at")
    private LocalDateTime createdAt; // 결제 준비 요청 시간
  }

  public static MultiValueMap<String, String> convertToMap(KakaoPrepare.Request request, ObjectMapper objectMapper) {
    return MultiValueMapConverter.convert(objectMapper, request);
  }
}

