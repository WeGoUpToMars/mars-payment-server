package com.mars.product.domain.repo;

import com.mars.product.domain.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  Product save(Product product);

  Optional<Product> findById(Long id);

  Optional<Product> findByName(String itemName);

  List<Product> findAll();

  List<Product> findByIds(List<Long> ids);
}
