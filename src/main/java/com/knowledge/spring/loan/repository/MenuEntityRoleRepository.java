package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.MenuEntityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuEntityRoleRepository extends JpaRepository<MenuEntityRole,Long> {
}
