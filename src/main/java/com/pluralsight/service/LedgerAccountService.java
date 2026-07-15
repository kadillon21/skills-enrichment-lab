package com.pluralsight.service;

import com.pluralsight.model.LedgerAccount;
import com.pluralsight.repository.LedgerAccountRepository;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class LedgerAccountService {
    private LedgerAccountRepository ledgerAccountRepository;

    public LedgerAccountService(LedgerAccountRepository ledgerAccountRepository) {
        this.ledgerAccountRepository = ledgerAccountRepository;
    }

    public List<LedgerAccount> getAll() {
        return ledgerAccountRepository.findAll();
    }

    public LedgerAccount create(LedgerAccount ledgerAccount) {
        return ledgerAccountRepository.save(ledgerAccount);
    }

    public LedgerAccount update(int id, LedgerAccount ledgerAccount) {
        LedgerAccount existing = ledgerAccountRepository.findById(id).get();
        existing.setName(ledgerAccount.getName());
        existing.setDescription(ledgerAccount.getDescription());
        return ledgerAccountRepository.save(existing);
    }

    public void delete(int id){
        LedgerAccount ledgerAccount = ledgerAccountRepository.findById(id).get();
        ledgerAccountRepository.delete(ledgerAccount);
    }

    public LedgerAccount findAllTransactions(int id) {
        return ledgerAccountRepository.findById(id).get();
    }
}

