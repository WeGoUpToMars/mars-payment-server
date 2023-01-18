package com.mars.product.infra;

import com.mars.product.domain.entity.Product;
import com.mars.product.domain.repo.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductJpa jpa;

  @Override
  public Product save(Product product) {
    return jpa.save(product);
  }

  @Override
  public Optional<Product> findById(Long id) {
    return jpa.findById(id);
  }

  @Override
  public List<Product> findAll() {
    return jpa.findAll();
  }

  @Override
  public List<Product> findByIds(List<Long> ids) {
    return jpa.findAllByIdIn(ids);
  }
}
