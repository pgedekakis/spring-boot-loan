package com.knowledge.spring.loan.service.dto;

import com.knowledge.spring.loan.model.Menu;
import com.knowledge.spring.loan.model.Role;
import com.knowledge.spring.loan.model.RoleMenu;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoleMenuDTO {
    private long id;
    private Menu menu;
    private Role role;

    public RoleMenuDTO(RoleMenu roleMenu) {
        this.setId(roleMenu.getId());
        this.setMenu(roleMenu.getMenu());
        this.setRole(roleMenu.getRole());
    }
}
