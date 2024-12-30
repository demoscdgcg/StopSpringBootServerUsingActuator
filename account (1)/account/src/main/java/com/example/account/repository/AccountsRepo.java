package com.example.account.repository;


import com.example.account.Model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepo extends JpaRepository<Accounts,Long> {
  Optional<Accounts> findByCustomerId(Long customerId);
}
