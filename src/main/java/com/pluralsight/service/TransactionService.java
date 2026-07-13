package com.pluralsight.service;

import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.model.Transaction;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    //Injecting repo into the service
    private final TransactionRepository transactionRepository;

    //Injecting a constructor
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
}
