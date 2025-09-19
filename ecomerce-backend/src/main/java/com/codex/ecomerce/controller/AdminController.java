package com.codex.ecomerce.controller;

import com.codex.ecomerce.domain.AccountStatus;
import com.codex.ecomerce.model.Seller;
import com.codex.ecomerce.services.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {
    private final SellerService sellerService;

    @PutMapping("/seller/{id}/status/{status}")
    public ResponseEntity<Seller> updateSellerStatus(
            @PathVariable Long id,
            @PathVariable AccountStatus status
            ) throws Exception {
        Seller updateSeller = sellerService.updateSellerAccountStatus(id, status);
        return ResponseEntity.ok(updateSeller);
    }
}
