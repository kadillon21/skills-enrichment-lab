package com.pluralsight.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    private LocalDate date;
    
    @NotNull
    private LocalTime time;

    @NotBlank
    private String description;

    @NotBlank
    private String vendor;
    
    @NotNull
    private double amount;

    @ManyToOne
    @JoinColumn(name = "ledger_account_id")
    private LedgerAccount ledgerAccount;

    protected Transaction() {}

    // Constructor for new transactions
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // All getters and setters for transaction object
    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    // checks if objects amount is > 0
    public boolean isDeposit() {
        return amount > 0;
    }

    // checks if objects amount is < 0
    public boolean isPayment() {
        return amount < 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime setTime(LocalTime time) {
        this.time = time;
        return time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LedgerAccount getLedgerAccount() {
        return ledgerAccount;
    }

    public void setLedgerAccount(LedgerAccount ledgerAccount) {
        this.ledgerAccount = ledgerAccount;
    }
}
