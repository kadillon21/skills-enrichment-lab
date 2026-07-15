package com.pluralsight.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ledger_accounts")
public class LedgerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name; // "Repair & Mechanic", "Marketing", "Sales", "Operations"

    private String description;

    protected LedgerAccount() {}

    @JsonCreator
    public LedgerAccount(@JsonProperty("name") String name, @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
