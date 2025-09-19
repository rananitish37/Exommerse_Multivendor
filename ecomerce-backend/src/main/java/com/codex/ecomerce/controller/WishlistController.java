package com.codex.ecomerce.controller;

import com.codex.ecomerce.exceptions.ProductException;
import com.codex.ecomerce.model.Product;
import com.codex.ecomerce.model.User;
import com.codex.ecomerce.model.Wishlist;
import com.codex.ecomerce.services.ProductService;
import com.codex.ecomerce.services.UserService;
import com.codex.ecomerce.services.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Wishlist> createWishlist(@RequestBody User user){
        Wishlist wishlist = wishlistService.createWishlist(user);
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping()
    public ResponseEntity<Wishlist> getWishlistByUserId(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserFromToken(jwt);

        Wishlist wishlist = wishlistService.getWishlistByUserId(user);

        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProductToWishlist(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Product product = productService.findProductById(productId);
        User user = userService.findUserFromToken(jwt);
        Wishlist updatedWishlist = wishlistService.addProductToWishlist(user,product);

        return ResponseEntity.ok(updatedWishlist);
    }
}

