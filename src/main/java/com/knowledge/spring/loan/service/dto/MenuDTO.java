package com.knowledge.spring.loan.service.dto;


import com.knowledge.spring.loan.model.Menu;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@Data
public class MenuDTO {
    private long id;
    private String description;
    private Date deactivationDate;
    private String path;

    public MenuDTO(Menu menu) {
        this.setId(menu.getId());
        this.setDescription(menu.getDescription());
        this.setDeactivationDate(menu.getDeactivationDate());
        this.setPath(menu.getPath());
    }
}
