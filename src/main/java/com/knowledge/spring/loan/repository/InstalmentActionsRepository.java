package com.knowledge.spring.loan.repository;


import com.knowledge.spring.loan.model.InstalmentActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InstalmentActionsRepository extends JpaRepository<InstalmentActions, Long> {

    @Query(value = "SELECT instalmentActions FROM InstalmentActions instalmentActions WHERE instalmentActions.paymentActions.id=:paymentActionsId")
    InstalmentActions getInstalmentActionsIds(@Param("paymentActionsId") Long paymentActionsId);
}
