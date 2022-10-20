package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.PaymentActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentActionsRepository extends JpaRepository<PaymentActions, Long> {

    @Query(value = "SELECT instalmentActions.id FROM InstalmentActions instalmentActions WHERE instalmentActions.paymentActions.id=:instalmentActionsId")
    List<Long> getInstalmentActionsIds(@Param("instalmentActionsId") Long instalmentActionsId);

}
