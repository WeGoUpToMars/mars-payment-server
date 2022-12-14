package com.mars.order.infra;

import com.mars.order.domain.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductJpa extends JpaRepository<OrderProduct, Long> {

}
