package com.mars.order.infra;

import com.mars.order.domain.entity.Order;
import com.mars.user.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpa extends JpaRepository<Order, Long> {

  Optional<Order> findByUser(User user);

  Optional<Order> findByOrderUuid(String orderUuid);
}
