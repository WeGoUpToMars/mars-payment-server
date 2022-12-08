package com.mars.product.application;

import com.mars.product.domain.entity.Product;
import com.mars.product.domain.repo.ProductRepository;
import com.mars.product.presentation.dto.ProductDto.ProductRequest;
import com.mars.product.presentation.dto.ProductDto.ProductResponse;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

  private final ProductRepository productRepository;

  /**
   * product 생성.
   */
  @Transactional
  public ProductResponse save(ProductRequest request) {
    return ProductResponse.toRes(createProduct(request));
  }

  private Product createProduct(ProductRequest request) {
    final Product productToSave = Product.create(request.getName(), request.getPrice(), request.getCategory());
    return productRepository.save(productToSave);
  }

  /**
   * product 전체 조회.
   */
  public List<ProductResponse> findAll() {
    return productRepository.findAll().stream().map(ProductResponse::toRes).collect(Collectors.toList());
  }

  /**
   * product 단건 조회.
   */
  public ProductResponse findById(Long id) {
    return productRepository.findById(id)
        .map(ProductResponse::toRes)
        .orElseGet(ProductResponse::empty);
  }

  /**
   * product 단건 업데이트.
   */
  @Transactional
  public ProductResponse update(Long id, ProductRequest request) {
    final Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);

    product.updateName(request.getName());
    product.updatePrice(request.getPrice());
    product.updateCategory(request.getCategory());

    return ProductResponse.toRes(product);
  }
}
