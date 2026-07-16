package com.pluralsight.controller;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.service.CsvImportService;
import com.pluralsight.service.TransactionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    public TransactionController(TransactionRepository transactionRepository, TransactionService transactionService) {
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }


    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity <Transaction> getById(@PathVariable Integer id) {
        Transaction transaction = transactionService.getById(id);

        if(transaction == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(transaction);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/search")
    public ResponseEntity<List<Transaction>> search(@RequestParam(name = "transactionId",required = false)Integer transactionId,
                                                    @RequestParam(name = "startDate", required = false )LocalDate startDate,
                                                    @RequestParam(name = "endDate", required = false )LocalDate endDate,
                                                    @RequestParam(name = "vendor", required = false)String vendor,
                                                    @RequestParam(name = "description", required = false)String description,
                                                    @RequestParam(name = "ledgerId",required = false)Integer ledgerId,
                                                    @RequestParam(name = "minAmount", required = false)Double minAmount,
                                                    @RequestParam(name = "maxAmount",required = false)Double maxAmount,
                                                    @RequestParam(name = "transType", required = false)String transType){
        return ResponseEntity.ok().body( transactionService.search(transactionId,minAmount,maxAmount,startDate,endDate,description,vendor,ledgerId,transType));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/export")
    public ResponseEntity<String> exportTransactions(){
        String cvsData = transactionService.exportCsv();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "Attachment; filename=\"tranactions.csv\"")
                .header(HttpHeaders.CONTENT_TYPE,"text/csv")
                .body(cvsData);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction){
        // Check if it exists first
        if (transactionService.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Transaction update = transactionService.updateTransaction(id, transaction);
        return ResponseEntity.ok().body(update);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
        Transaction create = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTranaction(@PathVariable Integer id){
        if(transactionService.getById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();

    }

}
