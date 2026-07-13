package com.pluralsight.controller;

import com.pluralsight.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {

    private final TransactionRepository transactionRepository;

    public ReportController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // TODO: port the date-range logic already sitting in service/Reports.java —
    // the filtering logic itself doesn't need to change, it just needs to read from
    // transactionRepository.findAll() instead of the old in-memory list, and return
    // the result instead of printing it to console.
}
