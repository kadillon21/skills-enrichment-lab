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
}
