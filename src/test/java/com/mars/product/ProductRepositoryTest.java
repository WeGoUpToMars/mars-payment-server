package com.mars.product;

import com.mars.common.test.MarsTest;
import com.mars.product.domain.entity.Product;
import com.mars.product.domain.entity.constant.ProductCategory;
import com.mars.product.domain.repo.ProductRepository;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@MarsTest
class ProductRepositoryTest {

  @PersistenceContext
  EntityManager em;
  @Autowired
  private ProductRepository productRepository;
  private Product product;

  @BeforeEach
  void each() {
    product = Product.create("food1", 1000L, ProductCategory.FOOD);
  }

  @Test
  @DisplayName("프로덕트 생성 테스트")
  void create() {
    final Product save = productRepository.save(product);

    assertAll(
            () -> assertEquals(1, productRepository.findAll().size()),
            () -> assertEquals(product.getName(), save.getName()),
            () -> assertEquals(product.getPrice(), save.getPrice()),
            () -> assertEquals(product.getCategory(), save.getCategory())
    );
  }

  @Test
  @Transactional
  @DisplayName("프로덕트 업데이트 테스트")
  void update() {
    final Product save = productRepository.save(product);
    save.updatePrice(2000L);
    em.flush();

    final Product product1 = productRepository.findById(save.getId()).orElseThrow(NoSuchElementException::new);
    assertEquals(2000L, product1.getPrice());
  }
}
