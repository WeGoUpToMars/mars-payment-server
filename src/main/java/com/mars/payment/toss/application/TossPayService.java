package com.mars.payment.toss.application;

import com.mars.payment.toss.domain.repository.TossPayRepository;
import com.mars.payment.toss.presentation.dto.TossCreatePayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TossPayService {
  private final TossPayRepository repository;

  @Transactional
  public void createPayment(TossCreatePayment createPayment) {
    repository.createPayment(createPayment);
  }
}
