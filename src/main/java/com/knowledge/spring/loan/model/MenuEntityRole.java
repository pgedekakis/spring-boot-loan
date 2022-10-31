package com.knowledge.spring.loan.model;

import com.knowledge.spring.loan.service.dto.RoleMenuDTO;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menu_entity_role")
public class MenuEntityRole {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "menu_entity",referencedColumnName = "id")
    private MenuEntity menuEntity;

    @ManyToOne
    @JoinColumn(name = "role_menu",referencedColumnName = "id")
    private RoleMenu roleMenu;

    @Column(name = "insert")
    private boolean insert;

    @Column(name = "update")
    private boolean update;

    @Column(name = "delete")
    private boolean delete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MenuEntity getMenuEntity() {
        return menuEntity;
    }

    public void setMenuEntity(MenuEntity menuEntity) {
        this.menuEntity = menuEntity;
    }

    public RoleMenu getRoleMenu() {
        return roleMenu;
    }

    public void setRoleMenu(RoleMenu roleMenu) {
        this.roleMenu = roleMenu;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
