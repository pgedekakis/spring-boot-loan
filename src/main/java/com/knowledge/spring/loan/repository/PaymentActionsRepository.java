package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.InstalmentActions;
import com.knowledge.spring.loan.model.InstalmentPayments;
import com.knowledge.spring.loan.model.PaymentActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentActionsRepository extends JpaRepository<PaymentActions, Long> {

    @Query(value = "SELECT instalmentActions FROM InstalmentActions instalmentActions WHERE instalmentActions.paymentActions.id=:paymentActionsId")
    InstalmentActions getInstalmentActionsIds(@Param("paymentActionsId") Long paymentActionsId);

    @Query(value = "SELECT instalmentPayments FROM InstalmentPayments instalmentPayments WHERE (instalmentPayments.remainingInterest<instalmentPayments.interest OR instalmentPayments.remainingCapital<instalmentPayments.capital) AND instalmentPayments.loan.id=:loanId ORDER BY instalmentPayments.id DESC")
    List<InstalmentPayments> getInstalmentPayments(@Param("loanId") Long loanId);
}
