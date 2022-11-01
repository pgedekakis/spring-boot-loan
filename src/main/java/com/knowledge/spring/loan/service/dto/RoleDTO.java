package com.knowledge.spring.loan.service.dto;


import com.knowledge.spring.loan.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@Data
public class RoleDTO {
    private long id;
    private String description;
    private Date deactivationDate;

    public RoleDTO(Role role) {
        this.setId(role.getId());
        this.setDescription(role.getDescription());
        this.setDeactivationDate(role.getDeactivationDate());
    }
}
