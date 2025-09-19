package com.codex.ecomerce.repository;

import com.codex.ecomerce.model.Product;
import com.codex.ecomerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByByProductId(Long productId);
}
