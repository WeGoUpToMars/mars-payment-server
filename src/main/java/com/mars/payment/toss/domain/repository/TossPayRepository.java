package com.mars.payment.toss.domain.repository;

import com.mars.payment.toss.presentation.dto.TossCancelPayment;
import com.mars.payment.toss.presentation.dto.TossConfirmPayment;
import com.mars.payment.toss.presentation.dto.TossCreatePayment;

public interface TossPayRepository {

  void createPayment(TossCreatePayment tossCreatePayment);

  void confirmPayment(TossConfirmPayment tossConfirmPayment);

  void cancelPayment(TossCancelPayment tossCancelPayment);
}
