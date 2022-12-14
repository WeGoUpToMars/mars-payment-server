package com.mars.order.domain.repo;

import com.mars.order.domain.entity.Order;
import com.mars.user.domain.entity.User;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

  Order save(Order order);

  Optional<Order> findByUser(User user);

  List<Order> findAll();

  Optional<Order> findByUuid(String orderUuid);
}
