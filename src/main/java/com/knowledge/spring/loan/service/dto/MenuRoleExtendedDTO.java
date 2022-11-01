package com.knowledge.spring.loan.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.knowledge.spring.loan.model.Menu;
import com.knowledge.spring.loan.model.Role;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Jacksonized
public class MenuRoleExtendedDTO extends MenuDTO implements Serializable {

    @JsonProperty("role")
    private Set<Role> roleList;

    public void setMenuDTO(MenuDTO menuDTO){
        this.setId(menuDTO.getId());
        this.setDescription(menuDTO.getDescription());
        this.setDeactivationDate(menuDTO.getDeactivationDate());
        this.setPath(menuDTO.getPath());
    }

    @JsonIgnore
    public MenuDTO getMenuDTO(){
        MenuDTO menuDTO=new MenuDTO();
        menuDTO.setPath(this.getPath());
        menuDTO.setDescription(this.getDescription());
        menuDTO.setDeactivationDate(this.getDeactivationDate());
        return menuDTO;
    }


}
