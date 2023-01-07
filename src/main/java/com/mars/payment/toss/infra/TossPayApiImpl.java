package com.mars.payment.toss.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.common.util.MultiValueMapConverter;
import com.mars.payment.toss.constant.TossPayConstant;
import com.mars.payment.toss.domain.repository.TossPayApi;
import com.mars.payment.toss.presentation.dto.TossCancelPayment;
import com.mars.payment.toss.presentation.dto.TossConfirmPayment;
import com.mars.payment.toss.presentation.dto.TossCreatePayment;
import com.mars.payment.toss.presentation.dto.TossPayment;
import com.mars.payment.toss.vo.TossClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPayApiImpl implements TossPayApi {
  private final TossClient client;
  private final ObjectMapper objectMapper;
  private final RestTemplate restTemplate;

  @Override
  public TossPayment createPayment(TossCreatePayment tossCreatePayment) {
    final HttpEntity<String> body = getRequestBody(tossCreatePayment);
    final ResponseEntity<TossPayment> response = restTemplate.postForEntity(TossPayConstant.getCreatePaymentUrl(), body, TossPayment.class);

    return response.getBody();
  }

  @Override
  public TossPayment confirmPayment(TossConfirmPayment tossConfirmPayment) {
    final HttpEntity<String> body = getRequestBody(tossConfirmPayment);
    final ResponseEntity<TossPayment> response = restTemplate.postForEntity(TossPayConstant.getConfirmPaymentUrl(), body, TossPayment.class);

    return response.getBody();
  }

  @Override
  public List<TossPayment.Cancel> cancelPayment(String paymentKey, TossCancelPayment tossCancelPayment) {
    final HttpEntity<String> body = getRequestBody(tossCancelPayment);
    final ResponseEntity<TossPayment> response = restTemplate.postForEntity(TossPayConstant.getCancelPaymentUrl(paymentKey), body, TossPayment.class);

    if (Objects.isNull(response.getBody())) {
      return Collections.emptyList();
    }

    return response.getBody().getCancels();
  }

  @Override
  public TossPayment orderInquiry(String orderId) {
    final ResponseEntity<TossPayment> response = restTemplate.exchange(TossPayConstant.getOrderInquiryUrl(orderId), HttpMethod.GET, getAuthorizationHeader(), TossPayment.class);

    return response.getBody();
  }

  @Override
  public TossPayment paymentInquiry(String paymentKey) {
    final ResponseEntity<TossPayment> response = restTemplate.exchange(TossPayConstant.getPaymentInquiryUrl(paymentKey), HttpMethod.GET, getAuthorizationHeader(), TossPayment.class);

    return response.getBody();
  }

  private <T> HttpEntity<String> getRequestBody(T dto) {
    final String params = MultiValueMapConverter.convertAsJson(objectMapper, dto);
    return new HttpEntity<>(params, client.getHeader());
  }

  private HttpEntity<Void> getAuthorizationHeader() {
    return new HttpEntity<>(client.getHeader());
  }
}
