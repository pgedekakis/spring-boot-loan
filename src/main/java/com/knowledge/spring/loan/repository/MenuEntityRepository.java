package com.knowledge.spring.loan.repository;

import com.knowledge.spring.loan.model.Menu;
import com.knowledge.spring.loan.model.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuEntityRepository extends JpaRepository<MenuEntity,Long> {

    @Query(value = "SELECT menuEntity.id FROM MenuEntity  menuEntity WHERE menuEntity.menu.id=:menuId")
    List<Long> getRoleMenuIds(@Param("menuId")Long menuId);

    @Query(value = "SELECT menuEntity.id FROM MenuEntity  menuEntity WHERE menuEntity.entityTable.id=:entityId")
    List<Long> getMenuRoleIds(@Param("entityId")Long entityId);

    @Query(value = "SELECT menuEntity FROM MenuEntity  menuEntity WHERE menuEntity.menu.id=:menuId")
    List<MenuEntity> getMenuEntity(@Param("menuId")Long menuId);
}
