package com.codex.ecomerce.services;

import com.codex.ecomerce.model.CartItem;

public interface CartItemService {
    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception;
    void removeCartItem(Long userId, Long cartItemId) throws Exception;
    CartItem findCartItemById(Long userId) throws Exception;
}
