package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction){
        transaction.setId(null);
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

    public List<Transaction> search(Integer transactionId , Double minAmount, Double maxAmount, LocalDate startDate,LocalDate endDate, String description, String vendor, Integer ledgerId,String transType){
        List<Transaction> transactions;
        if(transactionId != null){
            Transaction t = transactionRepository.findById(transactionId).orElse(null);
            transactions = (t != null) ? List.of(t) : List.of();
        }else {
            transactions = transactionRepository.findAll();
        }
        return transactions.stream()
                .filter(transaction -> minAmount == null || transaction.getAmount() >= minAmount )
                .filter(transaction -> maxAmount == null || transaction.getAmount() <= maxAmount)
                .filter(transaction -> startDate == null || !transaction.getDate().isBefore(startDate))
                .filter(transaction -> endDate == null ||!transaction.getDate().isAfter(endDate))
                .filter(transaction -> description == null || description.equalsIgnoreCase(transaction.getDescription()))
                .filter(transaction -> vendor == null || vendor.equalsIgnoreCase(transaction.getVendor()))
                .filter(transaction -> ledgerId == null || ledgerId.equals(transaction.getLedgerAccount().getId()))
                .filter(transaction -> {
                    if( transType == null || transType.isBlank()){
                        return true;
                    }
                    if(transType.equalsIgnoreCase("DEPOSITS")){
                        return transaction.isDeposit();
                    }
                    if (transType.equalsIgnoreCase("Payments")){
                        return transaction.isPayment();
                    }
                    return true;

                })

                .toList();
    }


public String exportCsv(){

        List<Transaction> allTransactions = transactionRepository.findAll();
        StringBuilder csvText = new StringBuilder();
        csvText.append("Date");
        csvText.append("|");
        csvText.append("Time");
        csvText.append("|");
        csvText.append("Description");
        csvText.append("|");
        csvText.append("Vendor");
        csvText.append("|");
        csvText.append("Amount");
        csvText.append("\n");

        for(Transaction t : allTransactions){
            csvText.append(t.getDate());
            csvText.append("|");
            csvText.append(t.getTime());
            csvText.append("|");
            csvText.append(t.getDescription());
            csvText.append("|");
            csvText.append(t.getVendor());
            csvText.append("|");
            csvText.append(t.getAmount());
            csvText.append("\n");

        }
        return csvText.toString();

}

    public void deleteTransaction(int transactionId){
         transactionRepository.deleteById(transactionId);
    }

}
