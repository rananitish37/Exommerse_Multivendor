package com.codex.ecomerce.services;

import com.codex.ecomerce.model.Cart;
import com.codex.ecomerce.model.CartItem;
import com.codex.ecomerce.model.Product;
import com.codex.ecomerce.model.User;

public interface CartService {

    public CartItem addCartItem(
            User user,
            Product product,
            String size,
            int quantity
    );

    public Cart findUserCart(User user);
}
