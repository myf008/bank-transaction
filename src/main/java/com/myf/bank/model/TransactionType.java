package com.myf.bank.model;

public enum TransactionType {
    DEPOSIT("存款"),
    WITHDRAWAL("取款"),
    TRANSFER("转账"),
    PAYMENT("支付"),
    CREDIT("信用卡充值"),
    DEBIT("信用卡消费"),
    INTEREST("利息"),
    FEE("费用"),
    LOAN("贷款"),
    REPAYMENT("还款");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}