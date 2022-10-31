package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.EntityTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityTableRepository extends JpaRepository<EntityTable,Long> {
}
