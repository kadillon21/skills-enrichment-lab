package com.pluralsight.controller;

import com.pluralsight.model.LedgerAccount;
import com.pluralsight.service.LedgerAccountService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ledger-accounts")
public class LedgerAccountController {
    
    private LedgerAccountService ledgerAccountService;
    
    public LedgerAccountController(LedgerAccountService ledgerAccountService) {
        this.ledgerAccountService = ledgerAccountService;
    }
    
    @GetMapping("")
    public ResponseEntity<List<LedgerAccount>> getAll() {
        return ResponseEntity.ok(ledgerAccountService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<LedgerAccount> create(@RequestBody LedgerAccount ledgerAccount) {
        LedgerAccount saved = ledgerAccountService.create(ledgerAccount);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LedgerAccount> update(@PathVariable int id, @RequestBody LedgerAccount ledgerAccount) {
        LedgerAccount updated = ledgerAccountService.update(id, ledgerAccount);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        ledgerAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
