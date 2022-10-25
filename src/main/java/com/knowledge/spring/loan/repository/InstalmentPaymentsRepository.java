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

    @Query(value = "SELECT instalmentPayments FROM InstalmentPayments instalmentPayments WHERE (instalmentPayments.remainingInterest<instalmentPayments.interest OR instalmentPayments.remainingCapital<instalmentPayments.capital) AND instalmentPayments.loan.id=:loanId ORDER BY instalmentPayments.id DESC")
    List<InstalmentPayments> getInstalmentPayments(@Param("loanId") Long loanId);

    @Query(value = "SELECT instalmentPayments.id FROM InstalmentPayments  instalmentPayments WHERE instalmentPayments.loan.id=:loanId")
    List<Long> getInstalmentPaymentsIds(@Param("loanId") Long loanId);

    @Query(value = "SELECT instalmentPayments.id FROM InstalmentPayments instalmentPayments WHERE instalmentPayments.loaner.id=:loanerId")
    List<Long> getPaymentsIds(@Param("loanerId") Long loanerId);
}
