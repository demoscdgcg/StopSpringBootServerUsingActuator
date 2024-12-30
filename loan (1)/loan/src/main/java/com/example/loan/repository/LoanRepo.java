package com.example.loan.repository;
import com.example.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepo extends JpaRepository<Loan,Long> {
    Optional<Loan> findByMobileNumber(String mobileNumber);
}
