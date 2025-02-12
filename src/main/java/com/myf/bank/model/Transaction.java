package com.myf.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * bank transaction entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    private String statementId;//交易流水号
    private BigDecimal amount;//金额
    private TransactionType type;//交易类型
    private LocalDateTime timestamp;//交易时间
    private String description;//交易描述
    private Long userId;//用户id
}