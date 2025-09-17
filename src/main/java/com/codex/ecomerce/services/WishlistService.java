package com.codex.ecomerce.services;

import com.codex.ecomerce.model.Product;
import com.codex.ecomerce.model.User;
import com.codex.ecomerce.model.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);


}
