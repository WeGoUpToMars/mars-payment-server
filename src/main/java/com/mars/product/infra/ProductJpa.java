package com.mars.product.infra;

import com.mars.product.domain.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpa extends JpaRepository<Product, Long> {
  List<Product> findAllByIdIn(List<Long> ids);
}
