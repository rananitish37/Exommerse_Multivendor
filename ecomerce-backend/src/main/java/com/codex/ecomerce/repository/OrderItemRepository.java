package com.codex.ecomerce.repository;

import com.codex.ecomerce.model.Order;
import com.codex.ecomerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
