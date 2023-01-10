package com.mars.payment.toss.application;

import com.mars.payment.toss.domain.repository.TossPayApi;
import com.mars.payment.toss.presentation.dto.TossCancelPayment;
import com.mars.payment.toss.presentation.dto.TossConfirmPayment;
import com.mars.payment.toss.presentation.dto.TossCreatePayment;
import com.mars.payment.toss.presentation.dto.TossPayment;
import com.mars.payment.toss.presentation.dto.TossPayment.Cancel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TossPayService {
  private final TossPayApi tossPayApi;

  @Transactional
  public TossPayment createPayment(TossCreatePayment createPayment) {
    return tossPayApi.createPayment(createPayment);
  }

  @Transactional
  public TossPayment confirmPayment(String paymentKey, String orderId, Long amount) {
    return tossPayApi.confirmPayment(TossConfirmPayment.create(paymentKey, orderId, amount));
  }

  @Transactional
  public List<Cancel> cancelPayment(String paymentKey, TossCancelPayment cancelPayment) {
    return tossPayApi.cancelPayment(paymentKey, cancelPayment);
  }

  public TossPayment orderInquiry(String orderId) {
    return tossPayApi.orderInquiry(orderId);
  }

  public TossPayment paymentInquiry(String paymentKey) {
    return tossPayApi.paymentInquiry(paymentKey);
  }
}
