package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }
}
