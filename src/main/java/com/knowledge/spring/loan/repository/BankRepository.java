package com.knowledge.spring.loan.repository;


import com.knowledge.spring.loan.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query(value = "SELECT loan.id FROM Loan loan WHERE loan.bank.id=:bankId")
    List<Long> getLoanIds(@Param("bankId") Long bankId);
}
