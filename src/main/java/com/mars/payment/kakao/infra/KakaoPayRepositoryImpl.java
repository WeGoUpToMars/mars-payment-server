package com.mars.payment.kakao.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.payment.kakao.domain.repository.KakaoPayRepository;
import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
@Slf4j
public class KakaoPayRepositoryImpl implements KakaoPayRepository {

  private static final String KAKAO_HOST = "https://kapi.kakao.com";

  private final ObjectMapper objectMapper;

  @Override
  public void preparePayment(KakaoPrepare.Request request) {
    final var restTemplate = new RestTemplate();
    final var headers = getKakaoPayHeader();
    final var params = KakaoPrepare.convertToMap(request, objectMapper);
    final var body = new HttpEntity<>(params, headers);

    try {
      final var response = restTemplate.postForObject(getKakaoPayPrepareUrl(), body, Response.class);
    } catch (RestClientException e) {
      log.error("카카오 결제 준비 과정에서 문제가 발생했습니다.", e);
    }
  }

  private String getKakaoPayPrepareUrl() {
    return KAKAO_HOST + "/v1/payment/order";
  }

  private static HttpHeaders getKakaoPayHeader() {
    final var headers = new HttpHeaders();
    headers.add("Authorization", "KakaoAK " + "cc062a76b9cd620043a26fd72b5f8c8d");
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
    return headers;
  }

  @Override
  public void approvePayment(KakaoApprove.Request request) {

  }
}
