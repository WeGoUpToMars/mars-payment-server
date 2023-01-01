package com.mars.payment.common.repository;

import com.mars.payment.common.dto.ApproveInfo;
import com.mars.payment.common.dto.InquiryInfo;
import com.mars.payment.common.dto.PrepareInfo;

public interface PaymentRepository {

  void preparePayment(PrepareInfo.Request prepareRequest);

  void inquiryPayment(InquiryInfo.Request inquiryRequest);

  void approvePayment(ApproveInfo.Request approveRequest);
}
