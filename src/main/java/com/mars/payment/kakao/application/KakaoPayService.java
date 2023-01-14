package com.mars.payment.kakao.application;

import com.mars.payment.kakao.domain.constant.KakaoExceptionInfo;
import com.mars.payment.kakao.domain.repository.KakaoPayApi;
import com.mars.payment.kakao.domain.repository.PaymentRepository;
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
  private final PaymentRepository paymentRepository;

  @Transactional
  public KakaoPrepare.Response preparePayment(KakaoPrepare.Request request) {
    return kakaoPayApi.preparePayment(request); // 사실 여기서도 마스만의 response를 반환하는 거임
  }

  @Transactional
  public String approvePayment(KakaoApprove.Request request) {
    final var savedPaymentInfo = paymentRepository.findByTid(request.getTid()).orElse(null);
    if (savedPaymentInfo == null) {
      throw KakaoExceptionInfo.NO_VALID_ORDER_EXISTS.exception();
    }
    final var kakaoResponse = kakaoPayApi.approvePayment(request);
    if (savedPaymentInfo.getTotalAmount().equals(kakaoResponse.getAmount().getTotal())) { // 여기에 부차적인 예외처리 추가되어야 한다
      throw KakaoExceptionInfo.NO_VALID_PAYMENT_INFO.exception();
    }
    return "payment approved"; // 사실 여기서도 마스만의 response를 반환하는 거임
  }
}
