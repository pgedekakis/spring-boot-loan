package com.knowledge.spring.loan.repository;


import com.knowledge.spring.loan.model.InstalmentActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstalmentActionsRepository extends JpaRepository<InstalmentActions, Long> {
}
