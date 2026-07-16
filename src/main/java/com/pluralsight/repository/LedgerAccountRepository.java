package com.pluralsight.repository;

import com.pluralsight.model.LedgerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LedgerAccountRepository extends JpaRepository<LedgerAccount, Integer> {
    Optional<LedgerAccount> findByNameIgnoreCase(String name);
}
