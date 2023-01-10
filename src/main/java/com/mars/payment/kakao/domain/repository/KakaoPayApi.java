package com.mars.payment.kakao.domain.repository;

import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare;

public interface KakaoPayApi {

  KakaoPrepare.Response preparePayment(KakaoPrepare.Request request);

  KakaoApprove.Response approvePayment(KakaoApprove.Request request);
}
