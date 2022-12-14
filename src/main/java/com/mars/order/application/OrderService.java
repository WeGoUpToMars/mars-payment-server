package com.mars.order.application;

import com.mars.order.domain.entity.Order;
import com.mars.order.domain.entity.OrderProduct;
import com.mars.order.domain.repo.OrderRepository;
import com.mars.order.presentation.dto.OrderDto.Request;
import com.mars.order.presentation.dto.OrderDto.Response;
import com.mars.product.domain.repo.ProductRepository;
import com.mars.user.domain.entity.User;
import com.mars.user.domain.repo.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
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
  public Response save(Request request) {
    final User user = userRepository.findByAccountId(request.getAccountId()).orElseThrow(NoSuchElementException::new);
    final Order order = orderRepository.save(Order.create(request.getOrderUuid(), request.getAmount(), request.getOrderStatus(), user));

    makeOrderProductMapping(request, order);
    return Response.of(order);
  }

  private void makeOrderProductMapping(Request request, Order order) {
    final List<OrderProduct> orderProducts = productRepository.findByIds(request.getProductIds()).stream()
                                                              .map(product -> OrderProduct.create(order, product))
                                                              .collect(Collectors.toList());

    order.addProduct(orderProducts);
  }

  public Response findByUser(String accountId) {
    final User user = userRepository.findByAccountId(accountId).orElseThrow(NoSuchElementException::new);

    return orderRepository.findByUser(user)
                          .map(Response::of)
                          .orElseGet(Response::empty);
  }

  public List<Response> findAll() {
    return orderRepository.findAll().stream()
                          .map(Response::of)
                          .collect(Collectors.toList());
  }

  public Response findByOrderUuid(String orderUuid) {
    return orderRepository.findByUuid(orderUuid)
                          .map(Response::of)
                          .orElseGet(Response::empty);
  }
}
