package com.pluralsight.service;

import com.pluralsight.model.LedgerAccount;
import com.pluralsight.model.Transaction;
import com.pluralsight.repository.LedgerAccountRepository;
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
import java.util.Optional;

@Service
public class CsvImportService {

    private final TransactionRepository transactionRepository;
    private final LedgerAccountRepository ledgerAccountRepository;

    public CsvImportService(TransactionRepository transactionRepository,
                             LedgerAccountRepository ledgerAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.ledgerAccountRepository = ledgerAccountRepository;
    }

    // date|time|description|vendor|amount|ledger_account_id
    // ledger_account_id may be blank — transaction just stays unassigned.
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

                    Transaction t = new Transaction(date, time, description, vendor, amount);

                    if (parts.length > 5 && !parts[5].isBlank()) {
                        Integer accountId = Integer.parseInt(parts[5].trim());
                        Optional<LedgerAccount> account = ledgerAccountRepository.findById(accountId);
                        if (account.isPresent()) {
                            t.setLedgerAccount(account.get());
                        } else {
                            errors.add("Line " + lineNumber + ": ledger account id " + accountId + " not found — imported without a category");
                        }
                    }

                    imported.add(t);
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
