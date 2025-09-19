package com.codex.ecomerce.controller;

import com.codex.ecomerce.exceptions.SellerException;
import com.codex.ecomerce.model.Seller;
import com.codex.ecomerce.model.Transaction;
import com.codex.ecomerce.services.SellerService;
import com.codex.ecomerce.services.TransactionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final SellerService sellerService;

    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getTransactionBySeller(
            @RequestHeader("Authorization") String jwt
    ) throws SellerException {
        Seller seller = sellerService.getSellerProfile(jwt);

        List<Transaction>  transactions = transactionService.getTransactionsBySellerId(seller);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions = transactionService.getAllTransactions();

        return ResponseEntity.ok(transactions);
    }

}
