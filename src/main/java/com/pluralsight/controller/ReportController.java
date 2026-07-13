package com.pluralsight.controller;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.service.Reports;
import com.pluralsight.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final Reports reportService;

    public ReportController(TransactionRepository transactionRepository, TransactionService transactionService, Reports reportService) {
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
        this.reportService = reportService;
    }

    @GetMapping("/month-to-date")
    public ResponseEntity<List<Transaction>> getMonthToDate() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        return ResponseEntity.ok(Reports.monthToDateReport(transactions));
    }

    // TODO: port the date-range logic already sitting in service/Reports.java —
    // the filtering logic itself doesn't need to change, it just needs to read from
    // transactionRepository.findAll() instead of the old in-memory list, and return
    // the result instead of printing it to console.
}
