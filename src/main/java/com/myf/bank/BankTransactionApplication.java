package com.myf.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication()
public class BankTransactionApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankTransactionApplication.class, args);
    }

}
