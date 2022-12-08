package com.mars.product.presentation;

import com.mars.product.application.ProductService;
import com.mars.product.presentation.dto.ProductDto.ProductRequest;
import com.mars.product.presentation.dto.ProductDto.ProductResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mars/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;


  @PostMapping("/")
  public ResponseEntity<ProductResponse> save(@RequestBody @Valid ProductRequest request) {
    return ResponseEntity.ok(productService.save(request));
  }

  @GetMapping("/")
  public ResponseEntity<List<ProductResponse>> findAll() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<ProductResponse> findById(@PathVariable(value = "id") Long id) {
    return ResponseEntity.ok(productService.findById(id));
  }

  @PutMapping("/id/{id}")
  public ResponseEntity<ProductResponse> update(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductRequest request) {
    return ResponseEntity.ok(productService.update(id, request));
  }
}
