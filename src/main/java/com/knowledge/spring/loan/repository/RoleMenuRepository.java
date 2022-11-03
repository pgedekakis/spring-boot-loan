package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu,Long> {


   @Query(value = "SELECT roleMenu.id FROM RoleMenu  roleMenu WHERE roleMenu.menu.id=:menuId")
    List<Long> getRoleMenuIds(@Param("menuId")Long menuId);

    @Query(value = "SELECT roleMenu.id FROM RoleMenu  roleMenu WHERE roleMenu.role.id=:roleId")
    List<Long> getMenuRoleIds(@Param("roleId")Long roleId);
    @Query(value = "SELECT roleMenu FROM RoleMenu  roleMenu WHERE roleMenu.role.id=:roleId")
    List<RoleMenu> getRoleMenu(@Param("roleId")Long roleId);

}
