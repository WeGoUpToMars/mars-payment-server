package com.mars.order.domain.entity;

import com.mars.common.entity.BaseEntity;
import com.mars.order.domain.entity.constant.OrderStatus;
import com.mars.product.domain.entity.Product;
import com.mars.user.domain.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String orderUuid;
  private Long amount;

  @Enumerated(value = EnumType.STRING)
  private OrderStatus status = OrderStatus.WAITED;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<OrderProduct> orderProducts = new ArrayList<>();

  private Order(String orderUuid, Long amount, User user) {
    this.orderUuid = orderUuid;
    this.amount = amount;
    this.user = user;
  }

  public static Order create(String orderUuid, Long amount, User user) {
    validateParams(orderUuid, amount, user);
    return new Order(orderUuid, amount, user);
  }

  private static void validateParams(String orderUuid, Long amount, User user) {
    Objects.requireNonNull(orderUuid);
    Objects.requireNonNull(amount);
    Objects.requireNonNull(user);

    if (amount <= 0) {
      throw new IllegalArgumentException("주문 금액은 0보다 커야 합니다");
    }
  }

  public List<Product> getProducts() {
    return orderProducts.stream()
                        .map(OrderProduct::getProduct)
                        .collect(Collectors.toList());
  }

  public void addProduct(List<OrderProduct> orderProducts) {
    final List<OrderProduct> orderProductsToSave = orderProducts.stream()
                                                                .filter(orderProduct -> !this.orderProducts.contains(orderProduct))
                                                                .collect(Collectors.toList());
    this.orderProducts.addAll(orderProductsToSave);
  }

  public void removeAllProduct() {
    this.orderProducts.clear();
  }

  public void updateProduct(List<OrderProduct> orderProducts) {
    Objects.requireNonNull(orderProducts);
    if (orderProducts.isEmpty()) {
      throw new IllegalArgumentException("적어도 한 개 이상의 상품이 담겨야 합니다.");
    }
    this.orderProducts = orderProducts;
  }

  public void updateOrderUuid(String orderUuid) {
    Objects.requireNonNull(orderUuid);
    this.orderUuid = orderUuid;
  }

  public void updateAmount(Long amount) {
    Objects.requireNonNull(amount);
    this.amount = amount;
  }

  public void isOrdered() {
    this.status = OrderStatus.ORDERED;
  }

  public void isProcessing() {
    this.status = OrderStatus.PROCESSING;
  }

  public void isCompleted() {
    this.status = OrderStatus.COMPLETED;
  }
}
