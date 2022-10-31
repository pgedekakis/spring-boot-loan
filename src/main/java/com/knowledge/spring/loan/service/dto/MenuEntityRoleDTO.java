package com.knowledge.spring.loan.service.dto;

import com.knowledge.spring.loan.model.MenuEntity;
import com.knowledge.spring.loan.model.MenuEntityRole;
import com.knowledge.spring.loan.model.RoleMenu;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MenuEntityRoleDTO {
    private long id;
    private MenuEntity menuEntity;
    private RoleMenu roleMenu;
    private boolean insert;
    private boolean update;
    private boolean delete;

    public MenuEntityRoleDTO(MenuEntityRole menuEntityRole) {
        this.setId(menuEntityRole.getId());
        this.setMenuEntity(menuEntityRole.getMenuEntity());
        this.setRoleMenu(menuEntityRole.getRoleMenu());
        this.setInsert(menuEntityRole.isInsert());
        this.setUpdate(menuEntityRole.isUpdate());
        this.setDelete(menuEntityRole.isDelete());
    }
}
