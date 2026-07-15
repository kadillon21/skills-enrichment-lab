package com.pluralsight.controller;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.service.ReportService;
import com.pluralsight.service.Reports;
import com.pluralsight.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {


    private final TransactionService transactionService;
    private final ReportService reportService;

    //Constructor
    @Autowired
    public ReportController( TransactionService transactionService, ReportService reportService) {
        this.transactionService = transactionService;
        this.reportService = reportService;
    }

    @GetMapping("/month-to-date")
    public ResponseEntity<List<Transaction>> getMonthToDate() {
        List<Transaction> transactions = transactionService.getAll();

        return ResponseEntity.ok(Reports.monthToDateReport(transactions));
    }

    @GetMapping("/previous-month")
    public ResponseEntity<List<Transaction>> getPreviousMonth() {
        List<Transaction> transactions = transactionService.getAll();
        return ResponseEntity.ok(Reports.previousMonthReport(transactions));
    }

    @GetMapping("/year-to-date")
    public ResponseEntity<List<Transaction>> getYearToDate(){
        List<Transaction> transactions = transactionService.getAll();

        return ResponseEntity.ok(Reports.yearToDate(transactions));
    }

    @GetMapping("/by-vendor")
    public ResponseEntity<List<Transaction>> getByVendor(@RequestParam String vendor){
        List<Transaction> transactions = transactionService.search(
                null,
                null,
                null,
                null,
                null,
                null,
                vendor,
                null,
                null
        );

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/summary-by-account")
    public ResponseEntity<Map<String, Double>> getSummaryByAccount() {

        List<Transaction> transactions = transactionService.getAll();

        Map<String, Double> summary = reportService.summaryByAccount(transactions);

        return ResponseEntity.ok(summary);
    }




    // TODO: port the date-range logic already sitting in service/Reports.java —
    // the filtering logic itself doesn't need to change, it just needs to read from
    // transactionRepository.findAll() instead of the old in-memory list, and return
    // the result instead of printing it to console.
}
