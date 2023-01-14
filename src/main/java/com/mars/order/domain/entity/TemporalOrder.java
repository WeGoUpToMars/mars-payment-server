package com.mars.order.domain.entity;

import lombok.Getter;

@Getter
public class TemporalOrder {

  private String uuid; // 가맹점 주문 번호
  private String cid; // 가맹점 코드
  private String itemName;
  private Integer quantity;
}
