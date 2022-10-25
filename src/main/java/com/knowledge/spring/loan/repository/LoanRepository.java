package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {


    @Query(value = "SELECT loan.id FROM Loan loan WHERE loan.loanType.id=:loanTypeId")
    List<Long> getLoanIdsByType(@Param("loanTypeId") Long loanTypeId);

    @Query(value = "SELECT loan.id FROM Loan loan WHERE loan.bank.id=:bankId")
    List<Long> getLoanIdsByBank(@Param("bankId") Long bankId);
}
