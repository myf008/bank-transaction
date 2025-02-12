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
public class HelloController {



    @GetMapping("/hello")
    public String hello() {
        log.info("hello");
        return "Hello World!";
    }
}