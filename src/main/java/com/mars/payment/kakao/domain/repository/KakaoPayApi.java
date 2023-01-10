package com.mars.payment.kakao.domain.repository;

import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare.Request;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare.Response;

public interface KakaoPayApi {

  Response preparePayment(Request request);

  void approvePayment(KakaoApprove.Request request);
}
