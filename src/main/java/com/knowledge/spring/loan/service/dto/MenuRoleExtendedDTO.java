package com.knowledge.spring.loan.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.knowledge.spring.loan.model.Menu;
import com.knowledge.spring.loan.model.Role;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@Jacksonized
public class MenuRoleExtendedDTO extends MenuDTO implements Serializable {

    @JsonProperty("role")
    private Role role;

    public void setMenuDTO(MenuDTO menuDTO){
        this.setId(menuDTO.getId());
        this.setDescription(menuDTO.getDescription());
        this.setDeactivationDate(menuDTO.getDeactivationDate());
        this.setPath(menuDTO.getPath());
    }

    @JsonIgnore
    public MenuDTO getMenuDTO(){
        MenuDTO menuDTO=new MenuDTO();
        menuDTO.setPath(menuDTO.getPath());
        menuDTO.setDescription(menuDTO.getDescription());
        menuDTO.setDeactivationDate(menuDTO.getDeactivationDate());
        return menuDTO;
    }


}
