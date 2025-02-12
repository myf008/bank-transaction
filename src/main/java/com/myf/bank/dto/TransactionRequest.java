package com.myf.bank.dto;

import com.myf.bank.model.TransactionType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @NotBlank(message = "StatementId cannot be blank")
    private String statementId;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;


    @NotNull(message = "Type cannot be null")
    private TransactionType type;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}