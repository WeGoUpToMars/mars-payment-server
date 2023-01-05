package com.mars.payment.kakao.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.payment.kakao.domain.repository.KakaoPayRepository;
import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoInquiry;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
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
      restTemplate.postForLocation(getKakaoPayPrepareUrl(), body);
    } catch (RestClientException e) {
      e.printStackTrace();
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
  public void inquiryPayment(KakaoInquiry.Request request) {

  }

  @Override
  public void approvePayment(KakaoApprove.Request request) {

  }
}
