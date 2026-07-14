package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        existing.setLedgerAccount(transaction.getLedgerAccount());
        return transactionRepository.save(existing);

    }

    public List<Transaction> search(Integer transactionId , Double minAmount, Double maxAmount, LocalTime time, LocalDate date, String description, String vendor, Integer ledgerId){
        List<Transaction> transactions = transactionId != null
                ? transactionRepository.findByTransactionId(transactionId)
                : transactionRepository.findAll();
        return transactions.stream()
                .filter(transaction -> minAmount == null || transaction.getAmount() >= minAmount )
                .filter(transaction -> maxAmount == null || transaction.getAmount() <= maxAmount)
                .filter(transaction -> time == null || time.equals(transaction.getTime()))
                .filter(transaction -> date == null || date.equals(transaction.getDate()))
                .filter(transaction -> description == null || description.equalsIgnoreCase(transaction.getDescription()))
                .filter(transaction -> vendor == null || vendor.equalsIgnoreCase(transaction.getVendor()))
                .filter(transaction -> ledgerId == null || ledgerId.equals(transaction.getLedgerAccount().getId()))
                .toList();
    }

   public void deleteTransaction(int transactionId){
         transactionRepository.deleteById(transactionId);
    }

}
