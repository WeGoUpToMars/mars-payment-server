package com.mars.payment.kakao.infrastructure;

import com.mars.payment.kakao.domain.repository.KakaoPayRepository;
import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoInquiry;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare.Request;

public class KakaoPayRepositoryImpl implements KakaoPayRepository {

  @Override
  public void preparePayment(Request request) {

  }

  @Override
  public void inquiryPayment(KakaoInquiry.Request request) {

  }

  @Override
  public void approvePayment(KakaoApprove.Request request) {

  }
}
