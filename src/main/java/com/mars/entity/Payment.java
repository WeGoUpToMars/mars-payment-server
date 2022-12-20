package com.mars.entity;

import com.mars.common.entity.BaseEntity;
import com.mars.order.domain.entity.Order;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @Enumerated(value = EnumType.STRING)
  private PaymentGateway paymentGateway;

  @Enumerated(value = EnumType.STRING)
  private PaymentStatus paymentStatus;

  @Enumerated(value = EnumType.STRING)
  private PaymentType paymentType;


  enum PaymentGateway {
    NAVER, TOSS
  }

  enum PaymentStatus {
    SUCCESS, FAILURE
  }

  enum PaymentType {
    CARD, RT_BANK, ONLINE_DEPOSIT
  }
}
