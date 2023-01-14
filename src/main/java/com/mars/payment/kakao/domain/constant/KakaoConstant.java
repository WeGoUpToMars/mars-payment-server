package com.mars.payment.kakao.domain.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoConstant {

  public static final String APPROVAL_URL = "/mars/payments/kakao/success";
  public static final String CANCEL_URL = "/mars/payments/kakao/cancel";
  public static final String FAIL_URL = "/mars/payments/kakao/fail";
}
