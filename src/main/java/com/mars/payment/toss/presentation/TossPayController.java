package com.mars.payment.toss.presentation;

import com.mars.payment.toss.application.TossPayService;
import com.mars.payment.toss.presentation.dto.TossCancelPayment;
import com.mars.payment.toss.presentation.dto.TossCreatePayment;
import com.mars.payment.toss.presentation.dto.TossPayment;
import com.mars.payment.toss.presentation.dto.TossPayment.Cancel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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
  public ResponseEntity<TossPayment> createPayment(@RequestBody TossCreatePayment createPayment) {
    return ResponseEntity.ok(service.createPayment(createPayment));
  }

  @GetMapping("/orders/{orderId}")
  public ResponseEntity<TossPayment> orderInquiry(@PathVariable("orderId") String orderId) {
    return ResponseEntity.ok(service.orderInquiry(orderId));
  }

  @GetMapping("/payments/{paymentKey}")
  public ResponseEntity<TossPayment> paymentInquiry(@PathVariable("paymentKey") String paymentKey) {
    return ResponseEntity.ok(service.paymentInquiry(paymentKey));
  }

  @PostMapping("/{paymentKey}/cancel")
  public ResponseEntity<List<Cancel>> cancelPayment(@PathVariable("paymentKey") String paymentKey,
                                                    @RequestBody TossCancelPayment cancelPayment) {
    return ResponseEntity.ok(service.cancelPayment(paymentKey, cancelPayment));
  }

  @GetMapping("/success")
  public ResponseEntity<TossPayment> success(@RequestParam("paymentKey") String paymentKey,
                                             @RequestParam("orderId") String orderId,
                                             @RequestParam("amount") Long amount) {
    log.info("paymentKey:{}\norderId:{}\namount{}", paymentKey, orderId, amount);
    return ResponseEntity.ok(service.confirmPayment(paymentKey, orderId, amount));
  }

  @GetMapping("/fail")
  public void fail(@RequestParam("code") Integer code,
                   @RequestParam("message") String message,
                   @RequestParam("orderId") String orderId) {
    log.info("code:{}\nmessage:{}\norderId{}", code, message, orderId);
  }
}
