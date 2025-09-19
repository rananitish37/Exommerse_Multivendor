package com.codex.ecomerce.repository;

import com.codex.ecomerce.domain.AccountStatus;
import com.codex.ecomerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller findByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus status);
}
