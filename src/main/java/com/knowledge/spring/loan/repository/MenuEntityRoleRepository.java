package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.MenuEntityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuEntityRoleRepository extends JpaRepository<MenuEntityRole, Long> {

    @Query(value = "SELECT menuEntityRole.id FROM MenuEntityRole  menuEntityRole WHERE menuEntityRole.roleMenu.menu.id=:menuId OR menuEntityRole.menuEntity.menu.id=:menuId")
    List<Long> getRoleMenuEntityIds(@Param("menuId") Long menuId);

    @Query(value = "SELECT menuEntityRole.id FROM MenuEntityRole  menuEntityRole WHERE menuEntityRole.roleMenu.role.id=:roleId")
    List<Long> getMenuRoleEntityIds(@Param("roleId") Long roleId);

    @Query(value = "SELECT menuEntityRole.id FROM MenuEntityRole  menuEntityRole WHERE menuEntityRole.roleMenu.role.id=:entityId OR menuEntityRole.menuEntity.entityTable.id=:entityId")
    List<Long> getEntityIds(@Param("entityId") Long entityId);

    @Query(value = "SELECT menuEntityRole.id FROM MenuEntityRole  menuEntityRole WHERE menuEntityRole.roleMenu.id=:roleMenuId")
    List<Long> getMenuRoleEntity(@Param("roleMenuId") Long roleMenuId);

    @Query(value = "SELECT menuEntityRole.id FROM MenuEntityRole  menuEntityRole WHERE menuEntityRole.menuEntity.id=:menuEntityId")
    List<Long> getMenuEntityIds(@Param("menuEntityId") Long menuEntityId);
}
