package com.mars.payment.kakao.presentation.controller;

import com.mars.payment.kakao.application.KakaoPayService;
import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mars/payments/kakao")
@Slf4j
public class KakaoPayController {

  private final KakaoPayService kakaoPayService;

  @PostMapping("/prepare")
  public ResponseEntity<KakaoPrepare.Response> preparePayment(@RequestBody KakaoPrepare.Request request) {
    return ResponseEntity.ok(kakaoPayService.preparePayment(request));
  }

  @PostMapping("/approve")
  public ResponseEntity<KakaoApprove.Response> approvePayment(@RequestBody KakaoApprove.Request request) {
    return ResponseEntity.ok(kakaoPayService.approvePayment(request));
  }
}
