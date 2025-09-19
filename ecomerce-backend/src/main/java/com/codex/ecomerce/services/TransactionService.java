package com.codex.ecomerce.services;

import com.codex.ecomerce.model.Order;
import com.codex.ecomerce.model.Seller;
import com.codex.ecomerce.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);

    List<Transaction> getTransactionsBySellerId(Seller seller);
    List<Transaction> getAllTransactions();
}
