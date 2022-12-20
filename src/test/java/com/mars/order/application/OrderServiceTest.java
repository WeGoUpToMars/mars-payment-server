package com.mars.order.application;

import com.mars.common.test.MarsTest;
import com.mars.fixture.FixtureEntity;
import com.mars.order.presentation.dto.OrderDto.Request;
import com.mars.order.presentation.dto.OrderDto.Response;
import com.mars.product.domain.entity.Product;
import com.mars.product.infra.ProductJpa;
import com.mars.user.domain.entity.User;
import com.mars.user.infra.UserJpa;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MarsTest
class OrderServiceTest {

  @Autowired
  private ProductJpa productJpa;
  @Autowired
  private UserJpa userJpa;
  @Autowired
  private OrderService orderService;

  private final User userA = FixtureEntity.userA();
  private final User userB = FixtureEntity.userB();
  private final List<Product> foodProducts = FixtureEntity.foodProducts();
  private final List<Product> electronicProducts = FixtureEntity.electronicProducts();

  @BeforeEach
  void beforeEach() {
    productJpa.saveAll(foodProducts);
    productJpa.saveAll(electronicProducts);
    userJpa.saveAll(List.of(userA, userB));
  }

  @Test
  @DisplayName("주문 생성 테스트")
  void test() {
    final Request request = Request.builder()
                                   .accountId(userA.getAccountId())
                                   .productIds(foodProducts.stream().map(Product::getId).collect(Collectors.toList()))
                                   .orderUuid("order-uuid1")
                                   .build();

    final Response response = orderService.save(request);

    assertAll(
            () -> assertEquals(foodProducts.stream().mapToLong(Product::getPrice).sum(), response.getAmount()),
            () -> assertEquals(foodProducts.size(), response.getProducts().size())
    );
  }

  @Test
  @DisplayName("주문 내역 조회 테스트")
  void test1() {
    final Request requestA = Request.builder()
                                    .accountId(userA.getAccountId())
                                    .productIds(foodProducts.stream().map(Product::getId).collect(Collectors.toList()))
                                    .orderUuid("order-uuid1")
                                    .build();

    final Request requestB = Request.builder()
                                    .accountId(userB.getAccountId())
                                    .productIds(electronicProducts.stream().map(Product::getId).collect(Collectors.toList()))
                                    .orderUuid("order-uuid2")
                                    .build();

    orderService.save(requestA);
    orderService.save(requestB);

    final List<Response> responseA = orderService.findByUser(userA.getAccountId());
    final List<Response> responseB = orderService.findByUser(userB.getAccountId());

    assertAll(
            () -> assertEquals(1, responseA.size()),
            () -> assertEquals(1, responseB.size()),
            () -> assertEquals(foodProducts.stream().mapToLong(Product::getPrice).sum(), responseA.get(0).getAmount()),
            () -> assertEquals(electronicProducts.stream().mapToLong(Product::getPrice).sum(), responseB.get(0).getAmount()),
            () -> assertEquals(foodProducts.size(), responseA.get(0).getProducts().size()),
            () -> assertEquals(electronicProducts.size(), responseB.get(0).getProducts().size())
    );
  }
}
