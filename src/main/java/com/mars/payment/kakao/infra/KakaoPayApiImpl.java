package com.mars.payment.kakao.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.payment.kakao.domain.constant.KakaoExceptionInfo;
import com.mars.payment.kakao.domain.repository.KakaoPayApi;
import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare;
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
public class KakaoPayApiImpl implements KakaoPayApi {

  private static final String KAKAO_HOST = "https://kapi.kakao.com";

  private final ObjectMapper objectMapper;

  @Override
  public KakaoPrepare.Response preparePayment(KakaoPrepare.Request request) {
    final var restTemplate = new RestTemplate();
    final var headers = getKakaoPayHeader();
    final var params = KakaoPrepare.convertToMap(request, objectMapper);
    final var body = new HttpEntity<>(params, headers);

    try {
      return restTemplate.postForObject(getKakaoPayPrepareUrl(), body, KakaoPrepare.Response.class);
    } catch (RestClientException e) {
      log.error("카카오 결제 준비 과정에서 문제가 발생했습니다.", e);
      throw KakaoExceptionInfo.CANNOT_PREPARE_KAKAO_PAY.exception();
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
  public KakaoApprove.Response approvePayment(KakaoApprove.Request request) {
    final var restTemplate = new RestTemplate();
    final var headers = getKakaoPayHeader();
    final var params = KakaoApprove.convertToMap(request, objectMapper);
    final var body = new HttpEntity<>(params, headers);

    try {
      return restTemplate.postForObject(getKakaoPayApproveUrl(), body, KakaoApprove.Response.class);
    } catch (RestClientException e) {
      log.error("카카오 결제 승인 과정에서 문제가 발생했습니다.", e);
      throw KakaoExceptionInfo.CANNOT_APPROVE_KAKAO_PAY.exception();
    }
  }

  private String getKakaoPayApproveUrl() {
    return KAKAO_HOST + "/v1/payment/approve";
  }
}
