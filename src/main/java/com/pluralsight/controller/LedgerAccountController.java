package com.pluralsight.controller;

import com.pluralsight.model.LedgerAccount;
import com.pluralsight.service.LedgerAccountService;
import com.pluralsight.repository.LedgerAccountRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        ledgerAccountService.getAll();
        return ResponseEntity.status(200).build();
    }

//     @PostMapping("")
//     public LedgerAccount create(LedgerAccount ledgerAccount) {
//         return ledgerAccountService.create(ledgerAccount);
//     }
//
//     @PutMapping("")
//     public ResponseEntity<LedgerAccount> update(LedgerAccount ledgerAccount) {
//         return ledgerAccountService.update(ledgerAccount);
//     }
//
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> delete(Long id) {
//         return ledgerAccountService.delete(id);
// }
}
