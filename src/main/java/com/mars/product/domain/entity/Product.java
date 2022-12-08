package com.mars.product.domain.entity;

import com.mars.common.entity.BaseEntity;
import com.mars.entity.OrderProduct;
import com.mars.product.domain.entity.constant.ProductCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Long price;

  @Enumerated(value = EnumType.STRING)
  private ProductCategory category;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private final List<OrderProduct> orderProducts = new ArrayList<>();

  private Product(String name, Long price, ProductCategory category) {
    this.name = name;
    this.price = price;
    this.category = category;
  }

  public static Product create(String name, Long price, ProductCategory category) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(price);
    Objects.requireNonNull(category);
    return new Product(name, price, category);
  }

  public void updateName(String name) {
    Objects.requireNonNull(name);
    this.name = name;
  }

  public void updatePrice(Long price) {
    Objects.requireNonNull(price);
    this.price = price;
  }

  public void updateCategory(ProductCategory category) {
    Objects.requireNonNull(category);
    this.category = category;
  }
}
