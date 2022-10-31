package com.knowledge.spring.loan.service.dto;


import com.knowledge.spring.loan.model.EntityTable;
import com.knowledge.spring.loan.model.Menu;
import com.knowledge.spring.loan.model.MenuEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MenuEntityDTO {
    private long id;
    private Menu menu;
    private EntityTable entityTable;

    public MenuEntityDTO(MenuEntity menuEntity) {
        this.setId(menuEntity.getId());
        this.setMenu(menuEntity.getMenu());
        this.setEntityTable(menuEntity.getEntityTable());
    }
}
