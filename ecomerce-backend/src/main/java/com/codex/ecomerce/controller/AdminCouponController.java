package com.codex.ecomerce.controller;

import com.codex.ecomerce.model.Cart;
import com.codex.ecomerce.model.Coupon;
import com.codex.ecomerce.model.User;
import com.codex.ecomerce.services.CartService;
import com.codex.ecomerce.services.CouponService;
import com.codex.ecomerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminCouponController {
    private final CouponService couponService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping("/apply")
    public ResponseEntity<Cart> applyCoupon(
            @RequestParam String apply,
            @RequestParam String code,
            @RequestParam double orderValue,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserFromToken(jwt);
        Cart cart;
        if(apply.equals("true")){
            cart = couponService.applyCoupon(code, orderValue,user);
        }else {
            cart = couponService.removeCoupon(code,user);
        }

        return ResponseEntity.ok(cart);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Coupon> createCoupon(
            @RequestBody Coupon coupon
    ){
        Coupon createdCoupon = couponService.createCoupon(coupon);

        return ResponseEntity.ok(createdCoupon);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteCoupon(
            @PathVariable Long id
    ) throws Exception {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("Coupon deleted successfully");
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Coupon>> getAllCoupon(){
        List<Coupon> coupons = couponService.getAllCoupon();

        return ResponseEntity.ok(coupons);
    }
}
