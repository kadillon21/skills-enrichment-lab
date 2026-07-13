package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction){
        transaction.setId(0);
        return transactionRepository.save(transaction);


    }


    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }

    public Transaction getById(int id){
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction updateTransaction(int transactionId, Transaction transaction){
        Transaction existing = transactionRepository.findById(transactionId).orElse(null);
        assert existing != null;
        existing.setDate(transaction.getDate());
        existing.setTime(transaction.getTime());
        existing.setAmount(transaction.getAmount());
        existing.setDescription(transaction.getDescription());
        existing.setVendor(transaction.getVendor());
        existing.isDeposit();
        existing.isPayment();
        return transactionRepository.save(existing);

    }

   public void deleteTransaction(int transactionId){
         transactionRepository.deleteById(transactionId);
    }

}
