package com.mars.payment.kakao.application;

import com.mars.payment.kakao.domain.repository.KakaoPayApi;
import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KakaoPayService {

  private final KakaoPayApi kakaoPayApi;

  @Transactional
  public KakaoPrepare preparePayment(KakaoPrepare.Request request) {
    return kakaoPayApi.preparePayment(request);
  }

  @Transactional
  public KakaoApprove approvePayment(KakaoApprove.Request request) {
    return kakaoPayApi.approvePayment(request);
  }
}
