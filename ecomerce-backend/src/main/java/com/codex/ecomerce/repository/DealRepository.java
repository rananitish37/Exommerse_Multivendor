package com.codex.ecomerce.repository;

import com.codex.ecomerce.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
