package com.mars.payment.common.repository;

import com.mars.payment.kakao.dto.ApproveInfo;
import com.mars.payment.kakao.dto.InquiryInfo;
import com.mars.payment.kakao.dto.PrepareInfo;

public interface PaymentRepository {

  void preparePayment(PrepareInfo.Request prepareInfo);

  void requestPayment(InquiryInfo.Request inquiryInfo);

  void approvePayment(ApproveInfo.Request approveInfo);
}
