package com.mars.payment.toss.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.common.util.MultiValueMapConverter;
import com.mars.payment.toss.constant.TossPayConstant;
import com.mars.payment.toss.domain.repository.TossPayRepository;
import com.mars.payment.toss.presentation.dto.TossCancelPayment;
import com.mars.payment.toss.presentation.dto.TossConfirmPayment;
import com.mars.payment.toss.presentation.dto.TossCreatePayment;
import com.mars.payment.toss.presentation.dto.TossPayment;
import com.mars.payment.toss.vo.TossClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TossPayRepositoryImpl implements TossPayRepository {
  private final TossClient client;
  private final ObjectMapper objectMapper;
  private final RestTemplate restTemplate;

  @Override
  public void createPayment(TossCreatePayment tossCreatePayment) {
    final String params = MultiValueMapConverter.convertAsJson(objectMapper, tossCreatePayment);
    final HttpEntity<String> body = new HttpEntity<>(params, client.getHeader());
    final ResponseEntity<TossPayment> response = restTemplate.postForEntity(TossPayConstant.getCreatePaymentUrl(), body, TossPayment.class);
    log.error("{}\n{}\n{}",response.getBody(), response.getStatusCode(), response.getHeaders());
  }

  @Override
  public void confirmPayment(TossConfirmPayment tossConfirmPayment) {

  }

  @Override
  public void cancelPayment(TossCancelPayment tossCancelPayment) {

  }
}
