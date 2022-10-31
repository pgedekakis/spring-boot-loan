package com.knowledge.spring.loan.service.dto;


import com.knowledge.spring.loan.model.EntityTable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@Data
public class EntityTableDTO {
    private long id;
    private String joinName;
    private Date deactivationDate;

    public EntityTableDTO(EntityTable entityTable) {
        this.setId(entityTable.getId());
        this.setJoinName(entityTable.getJoinName());
        this.setDeactivationDate(entityTable.getDeactivationDate());
    }
}
