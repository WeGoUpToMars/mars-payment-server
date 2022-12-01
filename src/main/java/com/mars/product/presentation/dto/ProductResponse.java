package com.mars.product.presentation.dto;

import com.mars.product.domain.entity.constant.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

  private Long id;
  private String name;
  private Long price;
  private ProductCategory category;
}
