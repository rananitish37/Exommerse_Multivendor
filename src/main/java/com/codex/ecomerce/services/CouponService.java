package com.codex.ecomerce.services;

import com.codex.ecomerce.model.Cart;
import com.codex.ecomerce.model.Coupon;
import com.codex.ecomerce.model.User;

import java.util.List;

public interface CouponService {
    Cart applyCoupon(String code, double orderValue, User user) throws Exception;
    Cart removeCoupon(String code, User user) throws Exception;
    Coupon findCouponById(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupon();
    void deleteCoupon(Long id) throws Exception;
}
