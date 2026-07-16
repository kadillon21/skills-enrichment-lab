package com.pluralsight.controller;

import com.pluralsight.model.LedgerAccount;
import com.pluralsight.service.LedgerAccountService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class LedgerAccountController {
    
    private LedgerAccountService ledgerAccountService;
    
    public LedgerAccountController(LedgerAccountService ledgerAccountService) {
        this.ledgerAccountService = ledgerAccountService;
    }
    
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<List<LedgerAccount>> getAll() {
        return ResponseEntity.ok(ledgerAccountService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<LedgerAccount> create(@RequestBody LedgerAccount ledgerAccount) {
        LedgerAccount saved = ledgerAccountService.create(ledgerAccount);
        return ResponseEntity.status(201).body(saved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LedgerAccount> update(@PathVariable int id, @RequestBody LedgerAccount ledgerAccount) {
        LedgerAccount updated = ledgerAccountService.update(id, ledgerAccount);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        ledgerAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
