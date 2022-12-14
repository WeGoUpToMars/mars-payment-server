package com.mars.order.application;

import com.mars.order.domain.entity.Order;
import com.mars.order.domain.entity.OrderProduct;
import com.mars.order.domain.repo.OrderRepository;
import com.mars.order.presentation.dto.OrderDto;
import com.mars.order.presentation.dto.OrderDto.Response;
import com.mars.product.domain.entity.Product;
import com.mars.product.domain.repo.ProductRepository;
import com.mars.user.domain.entity.User;
import com.mars.user.domain.repo.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  @Transactional
  public OrderDto.Response save(OrderDto.Request request) {
    final User user = userRepository.findByAccountId(request.getAccountId()).orElseThrow(NoSuchElementException::new);
    final List<Product> products = productRepository.findByIds(request.getProductIds());
    final long totalPrice = products.stream().mapToLong(Product::getPrice).sum();
    final Order order = orderRepository.save(Order.create(UUID.randomUUID().toString(), totalPrice, user));

    makeOrderProductMapping(products, order);
    return Response.of(order);
  }

  private void makeOrderProductMapping(List<Product> products, Order order) {
    final List<OrderProduct> orderProducts = products.stream()
                                                     .map(product -> OrderProduct.create(order, product))
                                                     .collect(Collectors.toList());

    order.addProduct(orderProducts);
  }

  public List<OrderDto.Response> findByUser(String accountId) {
    final User user = userRepository.findByAccountId(accountId).orElseThrow(NoSuchElementException::new);

    return orderRepository.findByUser(user).stream()
                          .map(Response::of)
                          .collect(Collectors.toList());
  }

  public List<OrderDto.Response> findAll() {
    return orderRepository.findAll().stream()
                          .map(Response::of)
                          .collect(Collectors.toList());
  }

  public OrderDto.Response findByOrderUuid(String orderUuid) {
    return orderRepository.findByUuid(orderUuid)
                          .map(Response::of)
                          .orElseGet(Response::empty);
  }
}
