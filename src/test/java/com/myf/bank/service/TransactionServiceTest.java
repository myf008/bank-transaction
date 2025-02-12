package com.myf.bank.service;

import com.myf.bank.model.Transaction;
import com.myf.bank.model.TransactionType;
import com.myf.bank.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepository repository;
    @InjectMocks
    private TransactionService service;
    private Transaction validTransaction;

    @BeforeEach
    void setUp() {
        validTransaction = new Transaction("1", BigDecimal.valueOf(100),
                TransactionType.DEPOSIT, LocalDateTime.now(), "Salary",1L);
    }

    @Test
    public void testCreateTransactionSuccess() {
        when(repository.save(validTransaction)).thenReturn(validTransaction);

        Transaction result = service.createTransaction(validTransaction);
        assertEquals(validTransaction, result);
        verify(repository).save(validTransaction);
    }

    @Test
    public void testCreateTransaction_DuplicateId() {
        when(repository.existsById("1")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> service.createTransaction(validTransaction));
    }

    @Test
    public void testDeleteTransactionSuccess() {
        when(repository.existsById("1")).thenReturn(true);
        service.deleteTransaction("1");
        verify(repository).deleteById("1");
    }

    @Test
    public void testUpdateTransaction_Success() {
        Transaction updatedTransaction = new Transaction("1", BigDecimal.valueOf(200),
                TransactionType.CREDIT, null, "Rent", 1L);
        when(repository.existsById("1")).thenReturn(true);
        when(repository.save(updatedTransaction)).thenReturn(updatedTransaction);

        Transaction result = service.updateTransaction("1", updatedTransaction);
        assertEquals("1", result.getStatementId());
        assertEquals(BigDecimal.valueOf(200), result.getAmount());
        verify(repository).save(updatedTransaction);
    }

    @Test
    public void testUpdateTransaction_NotFound() {
        when(repository.existsById("2")).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> service.updateTransaction("2", validTransaction));
    }

    @Test
    public void testGetAllTransactions_Pagination() {
        List<Transaction> mockTransactions = List.of(
                new Transaction("1", BigDecimal.valueOf(100), TransactionType.DEPOSIT, LocalDateTime.now(), "Salary", 1L),
                new Transaction("2", BigDecimal.valueOf(50), TransactionType.CREDIT, LocalDateTime.now(), "Coffee",1L)
        );
        when(repository.findUserAll(0, 10,1)).thenReturn(mockTransactions);

        List<Transaction> result = service.getAllTransactions(0, 10, 1);
        assertEquals(2, result.size());
        assertEquals("Salary", result.get(0).getDescription());
    }

    @Test
    public void testGetAllTransactions_EmptyPage() {
        when(repository.findUserAll(2, 10,1)).thenReturn(List.of());
        assertTrue(service.getAllTransactions(2, 10,1).isEmpty());
    }
}