package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, Long> {


}
