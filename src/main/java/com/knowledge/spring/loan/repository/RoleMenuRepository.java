package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu,Long> {

}
