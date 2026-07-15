package com.pluralsight.controller;

import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.service.CsvImportService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/transactions")
public class CsvImportController {

    private final CsvImportService csvImportService;
    
    public CsvImportController(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file) {
        try {
            csvImportService.importCsv(file);
            return ResponseEntity.ok("CSV imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing CSV: " + e.getMessage());
        }
    }
}
