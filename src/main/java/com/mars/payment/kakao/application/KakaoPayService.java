package com.mars.payment.kakao.application;

import com.mars.order.domain.repo.OrderRedis;
import com.mars.payment.kakao.domain.constant.KakaoConstant;
import com.mars.payment.kakao.domain.constant.KakaoExceptionInfo;
import com.mars.payment.kakao.domain.repository.KakaoPayApi;
import com.mars.payment.kakao.domain.repository.PaymentRepository;
import com.mars.payment.kakao.presentation.dto.KakaoApprove;
import com.mars.payment.kakao.presentation.dto.KakaoPrepare.Request;
import com.mars.payment.mars.domain.dto.PaymentApprove;
import com.mars.payment.mars.domain.dto.PaymentPrepare;
import com.mars.product.domain.repo.ProductRepository;
import com.mars.store.repository.StoreRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KakaoPayService {

  private final KakaoPayApi kakaoPayApi;
  private final PaymentRepository paymentRepository;
  private final StoreRepository storeRepository;
  private final OrderRedis orderRedis;
  private final ProductRepository productRepository;

  @Transactional
  public PaymentPrepare.Response preparePayment(PaymentPrepare.Request request) {
    final var store = storeRepository.findByName(request.getStoreName());
    final var order = orderRedis.findByAccountId(request.getAccountId());
    final var product = productRepository.findByName(request.getItemName()).orElseThrow(NoSuchElementException::new);
    if (product.getQuantity() < request.getQuantity()) {
      // 예외 터트리기
    }
    final var kakaoResponse = kakaoPayApi.preparePayment(Request.builder()
                                                                .cid(store.getCode())
                                                                .partnerOrderId(order.getUuid())
                                                                .partnerUserId(store.getAccountId())
                                                                .itemName(product.getName())
                                                                .quantity(request.getQuantity())
                                                                .totalAmount(product.getPrice() * request.getQuantity())
                                                                .taxFreeAmount(product.getPrice() * request.getQuantity())
                                                                .approvalUrl(request.getWindowLocation() + KakaoConstant.APPROVAL_URL)
                                                                .cancelUrl(request.getWindowLocation() + KakaoConstant.CANCEL_URL)
                                                                .failUrl(request.getWindowLocation() + KakaoConstant.FAIL_URL)
                                                                .build());
    // 적당히 저장
    return PaymentPrepare.Response.builder()
                                  .tid(kakaoResponse.getTid())
                                  .redirectAppUrl(kakaoResponse.getNextRedirectAppUrl())
                                  .redirectMobileUrl(kakaoResponse.getNextRedirectMobileUrl())
                                  .redirectPcUrl(kakaoResponse.getNextRedirectPcUrl())
                                  .androidAppScheme(kakaoResponse.getAndroidAppScheme())
                                  .iosAppScheme(kakaoResponse.getIosAppScheme())
                                  .build(); // 사실 여기서도 마스만의 response를 반환하는 거임
  }

  @Transactional
  public PaymentApprove.Response approvePayment(PaymentApprove.Request request) {
    final var savedPaymentInfo = paymentRepository.findByTid(request.getTid()).orElse(null);
    if (savedPaymentInfo == null) {
      throw KakaoExceptionInfo.NO_VALID_ORDER_EXISTS.exception();
    }

    final var kakaoResponse = kakaoPayApi.approvePayment(KakaoApprove.Request.builder()
                                                                             .cid(savedPaymentInfo.getCid())
                                                                             .tid(request.getTid())
                                                                             .partnerOrderId(savedPaymentInfo.getPartnerOrderId())
                                                                             .partnerUserId(savedPaymentInfo.getPartnerUserId())
                                                                             .pgToken(request.getPgToken())
                                                                             .build());
    if (savedPaymentInfo.getTotalAmount().equals(kakaoResponse.getAmount().getTotal())) { // 여기에 부차적인 예외처리 추가되어야 한다
      throw KakaoExceptionInfo.NO_VALID_PAYMENT_INFO.exception();
    }
    return PaymentApprove.Response.builder()
                                  .status("approved")
                                  .build(); // 사실 여기서도 마스만의 response를 반환하는 거임
  }
}
