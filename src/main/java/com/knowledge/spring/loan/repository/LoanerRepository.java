package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.Loaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanerRepository extends JpaRepository<Loaner, Long> {

    @Query(value = "SELECT paymentActions.id FROM PaymentActions paymentActions WHERE paymentActions.loaner.id=:loanerId")
    List<Long> getWorkingIds(@Param("loanerId") Long loanerId);

    @Query(value = "SELECT instalmentPayments.id FROM InstalmentPayments instalmentPayments WHERE instalmentPayments.loaner.id=:loanerId")
    List<Long> getPaymentsIds(@Param("loanerId") Long loanerId);
}
