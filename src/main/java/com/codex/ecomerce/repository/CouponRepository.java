package com.codex.ecomerce.repository;

import com.codex.ecomerce.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findByCoupon(String code);
}
