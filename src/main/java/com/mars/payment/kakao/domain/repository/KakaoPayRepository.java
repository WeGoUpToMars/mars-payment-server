package com.mars.payment.kakao.domain.repository;

import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare;

public interface KakaoPayRepository {

  void preparePayment(KakaoPrepare.Request request);

  void approvePayment(KakaoApprove.Request request);
}
