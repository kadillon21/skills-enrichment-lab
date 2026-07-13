package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImportService {

    private final TransactionRepository transactionRepository;

    public CsvImportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Same date|time|description|vendor|amount format as the old Ledger.loadTransactions() —
    // just reading from an uploaded file instead of disk.
    public ImportResult importCsv(MultipartFile file) throws IOException {
        List<Transaction> imported = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    String[] parts = line.split("\\|");
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    imported.add(new Transaction(date, time, description, vendor, amount));
                } catch (Exception e) {
                    errors.add("Line " + lineNumber + ": " + e.getMessage());
                }
            }
        }

        transactionRepository.saveAll(imported);
        return new ImportResult(imported.size(), errors);
    }

    public record ImportResult(int importedCount, List<String> errors) {}
}
