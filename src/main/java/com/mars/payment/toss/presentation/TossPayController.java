package com.mars.payment.toss.presentation;

import com.mars.payment.toss.application.TossPayService;
import com.mars.payment.toss.presentation.dto.TossCreatePayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <a href="https://docs.tosspayments.com/reference/js-sdk#%EA%B2%B0%EC%A0%9C-%EC%9A%94%EC%B2%AD-%EC%B2%98%EB%A6%AC">결제 요청 처리 컨트롤러</a>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mars/payments/toss")
@Slf4j
public class TossPayController {

  private final TossPayService service;

  @PostMapping("/create")
  public void createPayment(@RequestBody TossCreatePayment createPayment) {
    service.createPayment(createPayment);
  }

  @GetMapping("/success")
  public void success(
          @RequestParam("paymentKey") String paymentKey,
          @RequestParam("orderId") String orderId,
          @RequestParam("amount") Long amount) {
    log.error("{} {} {}", paymentKey, orderId, amount);
  }

  @GetMapping("/fail")
  public void fail(
          @RequestParam("code") Integer code,
          @RequestParam("message") String message,
          @RequestParam("orderId") String orderId) {
    log.error("{} {} {}", code, message, orderId);
  }
}
