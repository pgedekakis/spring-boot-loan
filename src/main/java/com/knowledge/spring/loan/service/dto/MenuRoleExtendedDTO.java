package com.knowledge.spring.loan.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.knowledge.spring.loan.model.EntityTable;
import com.knowledge.spring.loan.model.Role;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.Set;

@Data
@Jacksonized
public class MenuRoleExtendedDTO extends MenuDTO implements Serializable {

    @JsonProperty("role")
    private Set<Role> roleList;
    @JsonProperty("entity")
    private Set<EntityTable> entityList;

    public void setMenuDTO(MenuDTO menuDTO) {
        this.setId(menuDTO.getId());
        this.setDescription(menuDTO.getDescription());
        this.setDeactivationDate(menuDTO.getDeactivationDate());
        this.setPath(menuDTO.getPath());
    }

    @JsonIgnore
    public MenuDTO getMenuDTO() {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(this.getId());
        menuDTO.setPath(this.getPath());
        menuDTO.setDescription(this.getDescription());
        menuDTO.setDeactivationDate(this.getDeactivationDate());
        return menuDTO;
    }


}
