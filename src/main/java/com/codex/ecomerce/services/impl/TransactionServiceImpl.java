package com.codex.ecomerce.services.impl;

import com.codex.ecomerce.model.Order;
import com.codex.ecomerce.model.Seller;
import com.codex.ecomerce.model.Transaction;
import com.codex.ecomerce.repository.SellerRepository;
import com.codex.ecomerce.repository.TransactionRepository;

import com.codex.ecomerce.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Transaction createTransaction(Order order) {
        Seller seller =  sellerRepository.findById(order.getSellerId()).get();
        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);


        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
