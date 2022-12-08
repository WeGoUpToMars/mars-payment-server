package com.mars.product.presentation.dto;

import com.mars.product.domain.entity.Product;
import com.mars.product.domain.entity.constant.ProductCategory;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {

  @Getter
  @AllArgsConstructor(staticName = "of")
  @NoArgsConstructor
  public static class ProductRequest {

    @NotNull
    private String name;
    @NotNull
    private Long price;
    @NotNull
    private ProductCategory category;


  }

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ProductResponse {

    private Long id;
    private String name;
    private Long price;
    private ProductCategory category;

    public static ProductResponse toRes(Product product) {
      return ProductResponse.builder()
          .id(product.getId())
          .name(product.getName())
          .price(product.getPrice())
          .category(product.getCategory())
          .build();
    }

    public static ProductResponse empty() {
      return ProductResponse.builder().build();
    }
  }
}
