package com.mars.order.presentation.dto;

import com.mars.order.domain.entity.Order;
import com.mars.product.presentation.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDto {

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    @NotNull
    private String orderUuid;
    @NotNull
    private String accountId;
    @Builder.Default
    private List<Long> productIds = new ArrayList<>();
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response {

    private Long id;
    private String orderUuid;
    private Long amount;
    private List<ProductDto.ProductResponse> products;

    public static Response of(Order order) {
      return Response.builder()
                     .id(order.getId())
                     .orderUuid(order.getOrderUuid())
                     .amount(order.getAmount())
                     .products(order.getProducts().stream()
                                    .map(ProductDto.ProductResponse::of)
                                    .collect(Collectors.toList()))
                     .build();
    }

    public static Response empty() {
      return Response.builder().build();
    }
  }
}
