package com.mars.product.domain.repo;

import com.mars.product.domain.entity.Product;
import java.util.Optional;

public interface ProductRepository {

  Product save(Product product);

  Optional<Product> findById(Long id);
}
