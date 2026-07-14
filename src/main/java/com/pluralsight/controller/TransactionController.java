package com.pluralsight.controller;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.service.CsvImportService;
import com.pluralsight.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final CsvImportService csvImportService;

    public TransactionController(TransactionRepository transactionRepository, TransactionService transactionService, CsvImportService csvImportService) {
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
        this.csvImportService = csvImportService;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Transaction> getById(@PathVariable Integer id) {
        Transaction transaction = transactionService.getById(id);

        if(transaction == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(transaction);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction){
        // Check if it exists first
        if (transactionService.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Transaction update = transactionService.updateTransaction(id, transaction);
        return ResponseEntity.ok().body(update);
    }

    @PostMapping()
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
        Transaction create = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTranaction(@PathVariable Integer transactionId){
        if(transactionService.getById(transactionId) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();

    }

    // TODO: POST /import allow upload of a CSV file
    // TODO: PUT /{id} — reuse the getById() not-found pattern
    // TODO: DELETE /{id}
    // TODO: GET /search — accept SearchCriteria as query params. Start simple: findAll() +
    //       SearchCriteria.matches() in-memory like the old CLI did; upgrade to a JPA
    //       Specification later if the dataset gets big enough that this is slow.
}
