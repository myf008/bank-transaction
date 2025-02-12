//package com.myf.bank.controller;
//
//import com.myf.bank.BankTransactionApplication;
//import com.myf.bank.model.Transaction;
//import com.myf.bank.model.TransactionType;
//import com.myf.bank.service.TransactionService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@WebMvcTest(TransactionController.class)
//public class TransactionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TransactionService service;
//
//    @InjectMocks
//    private TransactionController transactionController;
//
//    private final Transaction sampleTransaction = new Transaction("1", BigDecimal.valueOf(100),
//            TransactionType.DEPOSIT, LocalDateTime.now(), "Salary", 1L);
//
//    @Test
//    public void testCreateTransaction_Success() throws Exception {
//        when(service.createTransaction(any())).thenReturn(sampleTransaction);
//
//        mockMvc.perform(post("/transactions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"statementId\":\"1\",\"amount\":100,\"type\":\"DEPOSIT\",\"description\":\"Salary\"}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.statementId").value("1"))
//                .andExpect(jsonPath("$.amount").value(100))
//                .andExpect(jsonPath("$.type").value("DEPOSIT"))
//                .andExpect(jsonPath("$.description").value("Salary"));
//    }
//
//    @Test
//    public void testCreateTransaction_MissingRequiredField() throws Exception {
//        mockMvc.perform(post("/transactions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"type\":\"DEPOSIT\"}")) // Missing "amount"
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testDeleteTransaction_Success() throws Exception {
//        doNothing().when(service).deleteTransaction("1");
//        mockMvc.perform(delete("/transactions/1"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void testDeleteTransaction_NotFound() throws Exception {
//        doThrow(new NoSuchElementException("Not found")).when(service).deleteTransaction("99");
//        mockMvc.perform(delete("/transactions/99"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testUpdateTransaction_Success() throws Exception {
//        when(service.updateTransaction(eq("1"), any())).thenReturn(sampleTransaction);
//
//        mockMvc.perform(put("/transactions/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"statementId\":\"1\",\"amount\":200,\"type\":\"WITHDRAWAL\",\"description\":\"Rent\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.amount").value(100)); // Original value remains
//    }
//
//    @Test
//    public void testUpdateTransaction_InvalidBody() throws Exception {
//        mockMvc.perform(put("/transactions/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"amount\":\"invalid\"}")) // Invalid amount format
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testListTransactions_Success() throws Exception {
//        when(service.getAllTransactions(0, 10, 1)).thenReturn(List.of(sampleTransaction));
//
//        mockMvc.perform(get("/transactions?page=0&size=10"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].statementId").value("1"));
//    }
//
//    @Test
//    public void testListTransactions_InvalidPagination() throws Exception {
//        mockMvc.perform(get("/transactions?page=-1&size=0")) // Invalid parameters
//                .andExpect(status().isOk()); // Spring会自动修正为默认值，因此仍返回200
//    }
//}
