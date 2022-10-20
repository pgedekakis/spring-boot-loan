package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.InstalmentPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstalmentPaymentsRepository extends JpaRepository<InstalmentPayments, Long> {

    @Query(value = "SELECT instalmentPayments FROM InstalmentPayments instalmentPayments WHERE (instalmentPayments.remainingCapital>0  OR instalmentPayments.remainingInterest>0) AND instalmentPayments.loan.id=:loanId ORDER BY instalmentPayments.id ASC")
    List<InstalmentPayments> getPaidInstalmentPaymentId(@Param("loanId") Long loanId);
}
