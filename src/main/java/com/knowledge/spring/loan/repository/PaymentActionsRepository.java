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

    @Query(value = "SELECT paymentActions.id FROM PaymentActions paymentActions WHERE paymentActions.loaner.id=:loanerId")
    List<Long> getWorkingIds(@Param("loanerId") Long loanerId);
}
