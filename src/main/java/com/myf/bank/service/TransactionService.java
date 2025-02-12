package com.myf.bank.service;

import com.myf.bank.model.Transaction;
import com.myf.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    @CacheEvict(value = "transactions", allEntries = true)
    public Transaction createTransaction(Transaction transaction) {
        if (transactionRepository.existsById(transaction.getStatementId())) {
            throw new IllegalArgumentException("Transaction ID already exists");
        }
        return transactionRepository.save(transaction);
    }

    @CacheEvict(value = "transactions", allEntries = true)
    public void deleteTransaction(String statementId) {
        if (!transactionRepository.existsById(statementId)) {
            throw new NoSuchElementException("Transaction not found");
        }
        transactionRepository.deleteById(statementId);
    }

    @CacheEvict(value = "transactions", allEntries = true)
    public Transaction updateTransaction(String statementId, Transaction transaction) {
        if (!transactionRepository.existsById(statementId)) {
            throw new NoSuchElementException("Transaction not found");
        }
        transaction.setStatementId(statementId);
        return transactionRepository.save(transaction);
    }

    @Cacheable("transactions")
    public List<Transaction> getAllTransactions(int page, int size, long userId) {
        return transactionRepository.findUserAll(page, size, userId);
    }
}