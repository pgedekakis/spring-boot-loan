package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {


    @Query(value = "SELECT instalmentPayments.id FROM InstalmentPayments  instalmentPayments WHERE instalmentPayments.loan.id=:loanId")
    List<Long> getInstalmentPaymentsIds(@Param("loanId") Long loanId);
}
