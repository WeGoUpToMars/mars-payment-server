package com.mars.payment.kakao.presentation.controller;

import com.mars.payment.kakao.application.KakaoPayService;
import com.mars.payment.mars.domain.dto.PaymentApprove;
import com.mars.payment.mars.domain.dto.PaymentPrepare;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<PaymentPrepare.Response> preparePayment(@RequestBody PaymentPrepare.Request request) {
    return ResponseEntity.ok(kakaoPayService.preparePayment(request));
  }

  @PostMapping("success")
  public ResponseEntity<PaymentApprove.Response> success(@RequestBody PaymentApprove.Request request) {
    return ResponseEntity.ok(kakaoPayService.approvePayment(request));
  }

  @GetMapping("fail")
  public ResponseEntity<String> fail() {
    return ResponseEntity.ok("결제가 실패했습니다.");
  }

  @PutMapping("cancel")
  public ResponseEntity<String> cancel() {
    return ResponseEntity.ok("결제가 취소되었습니다.");
  }
}
