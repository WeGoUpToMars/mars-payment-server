package com.mars.order.infra;

import com.mars.order.domain.entity.Order;
import com.mars.order.domain.repo.OrderRepository;
import com.mars.user.domain.entity.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderJpa orderJpa;

  @Override
  public Order save(Order order) {
    return orderJpa.save(order);
  }

  @Override
  public List<Order> findByUser(User user) {
    return orderJpa.findByUser(user);
  }

  @Override
  public List<Order> findAll() {
    return orderJpa.findAll();
  }

  @Override
  public Optional<Order> findByUuid(String orderUuid) {
    return orderJpa.findByOrderUuid(orderUuid);
  }
}
