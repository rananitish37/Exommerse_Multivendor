package com.codex.ecomerce.repository;

import com.codex.ecomerce.model.Cart;
import com.codex.ecomerce.model.CartItem;
import com.codex.ecomerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
