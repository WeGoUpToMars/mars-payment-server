package com.mars.payment.kakao.dto;

import com.mars.payment.common.dto.ApproveInfo;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoApproveInfo {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Request implements ApproveInfo.Request {
    private String cid; // 가맹점 코드
    private String tid; // 결제 고유번호
    private String partnerOrderId; // 가맹정 주문 번호
    private String partnerUserId; // 가맹점 회원 id
    private String pgToken; // 결제 승인 요청을 인증하는 토큰
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response implements ApproveInfo.Response {
    private String aid; // 요청 고유 번호
    private String tid; // 결제 고유 번호
    private String cid; // 가맹점 코드
    private String sid; // 정기 결제용 ID
    private String partnerOrderId; // 가맹점 주문 번호
    private String partnerUserId; // 가맹점 회원 id
    private String parymentMethodType; // 결제 수단, CARD or MONEY
    private AmountDto amount; // 결제 금액 정보
    private CardInfoDto cardInfo; // 결제 상세 정보
    private String itemName; // 상품 이름
    private String itemCode; // 상품 코드
    private Integer quantity; // 상품 수량
    private LocalDateTime createdAt; // 결제 준비 요청 시각
    private LocalDateTime approvedAt; // 결제 승인 시각
    private String payload; // 결제 승인 요청에 대해 저장한 값
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class AmountDto {
    private Integer total; // 전체 결제 금액
    private Integer taxFree; // 비과세 금액
    private Integer vat; // 부가세 금액
    private Integer point; // 사용한 포인트 금액
    private Integer discount; // 할인 금액
    private Integer greenDiscount; // 컵 보증금
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class CardInfoDto {
    private String purchaseCorp; // 매입 카드사 한글명
    private String purchaseCorpCode; // 매입 카드사 코드
    private String issuerCorp; // 카드 발급사 한글명
    private String issuerCorpCode; // 카드 발급사 코드
    private String kakaopayPurchaseCorp; // 카카오페이 매입사명
    private String kakaopayPurchaseCoprCode; // 카카오페이 매입사 코드
    private String kakaopayIssuerCorp; // 카카오페이 발급사명
    private String kakaopayIssuerCorpCode; // 카카오페이 발급사 코드
    private String bin; // 카드 BIN
    private String cardType; // 카드 타입
    private String installMonth; // 할부 개월 수
    private String approvedId; // 카드사 승인번호
    private String cardMid; // 카드사 가맹점 번호
    private String interestFreeInstall; // 무이자할부 여부 (Y/N)
    private String cardItemCode; // 카드 상품 코드
  }
}
