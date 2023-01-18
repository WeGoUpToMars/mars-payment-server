package com.mars.payment.toss.domain.repository;

import com.mars.payment.toss.presentation.dto.TossCancelPayment;
import com.mars.payment.toss.presentation.dto.TossConfirmPayment;
import com.mars.payment.toss.presentation.dto.TossCreatePayment;
import com.mars.payment.toss.presentation.dto.TossPayment;
import com.mars.payment.toss.presentation.dto.TossPayment.Cancel;
import java.util.List;

public interface TossPayApi {

  TossPayment createPayment(TossCreatePayment tossCreatePayment);

  TossPayment confirmPayment(TossConfirmPayment tossConfirmPayment);

  List<Cancel> cancelPayment(String paymentKey, TossCancelPayment tossCancelPayment);

  TossPayment orderInquiry(String orderId);

  TossPayment paymentInquiry(String paymentKey);
}
