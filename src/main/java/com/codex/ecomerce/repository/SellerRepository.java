package com.codex.ecomerce.repository;

import com.codex.ecomerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller findByEmail(String email);
}
