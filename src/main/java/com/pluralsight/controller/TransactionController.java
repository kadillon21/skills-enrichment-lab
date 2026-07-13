package com.pluralsight.controller;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.service.CsvImportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final CsvImportService csvImportService;

    public TransactionController(TransactionRepository transactionRepository, CsvImportService csvImportService) {
        this.transactionRepository = transactionRepository;
        this.csvImportService = csvImportService;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Transaction create(@Valid @RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

//    @PutMapping
//    public Transaction updateTransaction(@RequestParam Integer transactionId){
//
//
//    }

    // TODO: POST /import allow upload of a CSV file
    // TODO: PUT /{id} — reuse the getById() not-found pattern
    // TODO: DELETE /{id}
    // TODO: GET /search — accept SearchCriteria as query params. Start simple: findAll() +
    //       SearchCriteria.matches() in-memory like the old CLI did; upgrade to a JPA
    //       Specification later if the dataset gets big enough that this is slow.
}
