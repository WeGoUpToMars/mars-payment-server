package com.mars.order.domain.repo;

import com.mars.order.domain.entity.TemporalOrder;

public interface OrderRedis {

  TemporalOrder findByAccountId(String accountId);
}
