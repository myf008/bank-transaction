package com.myf.bank.controller;

import com.myf.bank.dto.TransactionRequest;
import com.myf.bank.model.Transaction;
import com.myf.bank.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@RequestBody @Valid TransactionRequest request){
        log.info("createTransaction request={}", request);
        //userId一般从上下文中获取，此处模拟userI固定为1
        Long userId = 1L;
        Transaction transaction = Transaction.builder()
                .statementId(UUID.randomUUID().toString())
                .type(request.getType())
                .amount(request.getAmount())
                .timestamp(LocalDateTime.now())
                .description(request.getDescription())
                .userId(userId)
                .build();
        return transactionService.createTransaction(transaction);
    }

    @DeleteMapping("/{statementId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable String statementId) {
        log.info("deleteTransaction,statementId={}", statementId);
        transactionService.deleteTransaction(statementId);
    }

    @PutMapping("/{statementId}")
    public Transaction updateTransaction(@PathVariable String statementId,
                                       @Valid @RequestBody TransactionRequest request) {
        log.info("updateTransaction, statementId={},request={}", statementId, request);
        Transaction transaction = Transaction.builder()
                .statementId(statementId)
                .type(request.getType())
                .amount(request.getAmount())
                .timestamp(LocalDateTime.now())
                .description(request.getDescription())
                .userId(1L)
                .build();
        return transactionService.updateTransaction(statementId, transaction);
    }

    @GetMapping
    public List<Transaction> listTransactions(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        //userId一般从上下文中获取，此处模拟userI固定为1
        long userId = 1L;
        log.info("listTransactions, page={},size={},userId={}", page, size, userId);
        return transactionService.getAllTransactions(page, size,userId);
    }
}