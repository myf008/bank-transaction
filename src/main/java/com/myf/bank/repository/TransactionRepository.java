package com.myf.bank.repository;

import com.myf.bank.model.Transaction;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Repository
public class TransactionRepository {
    private final Map<String, Transaction> transactions = new ConcurrentHashMap<>();

    /**
     * 保存交易记录
     *
     * @param transaction 要保存的交易对象，不能为空
     * @return 返回保存后的交易对象，即输入参数
     */
    public Transaction save(Transaction transaction) {
        transactions.put(transaction.getStatementId(), transaction);
        return transaction;
    }

    /**
     * 根据交易ID查找交易信息
     *
     * @param statementId 交易流水ID，用于查找特定的交易信息
     * @return Optional<Transaction> 包含可能的交易对象的Optional，如果找不到则为Optional.empty()
     */
    public Optional<Transaction> findById(String statementId) {
        return Optional.ofNullable(transactions.get(statementId));
    }

    public List<Transaction> findUserAll(int page, int size, long userId) {
        List<Transaction> all = new ArrayList<>(transactions.values());
        int start = page * size;
        int end = Math.min(start + size, all.size());
        return all.subList(start, end).stream()
                .filter(t -> t.getUserId().equals(userId))
                .collect(Collectors.toList());
    }


    /**
     * 删除交易
     * @param statementId
     */
    public void deleteById(String statementId) {
        transactions.remove(statementId);
    }

    /**
     * 交易是否存在
     * @param statementId
     * @return
     */
    public boolean existsById(String statementId) {
        return transactions.containsKey(statementId);
    }
}