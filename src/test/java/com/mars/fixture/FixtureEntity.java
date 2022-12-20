package com.mars.fixture;

import com.mars.product.domain.entity.Product;
import com.mars.product.domain.entity.constant.ProductCategory;
import com.mars.user.domain.entity.User;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FixtureEntity {

  public static User userA() {
    return User.create("yong", "dfdfdf", "qwe!@#123", "dfdf@gmail.com", "/userA.jpg");
  }

  public static User userB() {
    return User.create("joon", "fefefe", "asd!@#123", "fefe@gmail.com", "/userB.jpg");
  }

  public static List<Product> foodProducts() {
    return List.of(
            Product.create("foodA", 1000L, ProductCategory.FOOD),
            Product.create("foodB", 2000L, ProductCategory.FOOD),
            Product.create("foodC", 3000L, ProductCategory.FOOD)
    );
  }

  public static List<Product> electronicProducts() {
    return List.of(
            Product.create("electC", 4000L, ProductCategory.ELECTRONIC),
            Product.create("electC", 5000L, ProductCategory.ELECTRONIC),
            Product.create("electC", 6000L, ProductCategory.ELECTRONIC)
    );
  }
}
