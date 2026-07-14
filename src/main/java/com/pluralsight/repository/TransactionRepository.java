package com.pluralsight.repository;

import com.pluralsight.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByVendorContainingIgnoreCase(String vendor);
    List<Transaction> findByLedgerAccount_Id(Integer ledgerAccountId);
    List<Transaction> findByTransactionId(Integer transactionId);
    // TODO: add more derived queries as filters get built out
}
