package com.mars.order.presentation;

import com.mars.order.application.OrderService;
import com.mars.order.presentation.dto.OrderDto;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mars/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping("/")
  public ResponseEntity<OrderDto.Response> save(@RequestBody @Valid OrderDto.Request request) {
    return ResponseEntity.ok(orderService.save(request));
  }

  @GetMapping("/")
  public ResponseEntity<List<OrderDto.Response>> findAll() {
    return ResponseEntity.ok(orderService.findAll());
  }

  @GetMapping("/{orderUuid}")
  public ResponseEntity<OrderDto.Response> findByOrderUuid(@PathVariable(value = "orderUuid") String orderUuid) {
    return ResponseEntity.ok(orderService.findByOrderUuid(orderUuid));
  }

  @GetMapping("/users/{accountId}")
  public ResponseEntity<List<OrderDto.Response>> findByUser(@PathVariable(value = "accountId") String accountId) {
    return ResponseEntity.ok(orderService.findByUser(accountId));
  }
}
