package com.mars.payment.kakao.domain.repository;

import com.mars.payment.kakao.presentation.dto.KakaoPrepare;
import java.util.Optional;

public interface PaymentRepository {

  Optional<KakaoPrepare.Request> findByTid(String tid);
}
