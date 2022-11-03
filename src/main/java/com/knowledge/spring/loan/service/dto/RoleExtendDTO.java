package com.knowledge.spring.loan.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

@Data
@Jacksonized
public class RoleExtendDTO extends RoleDTO implements Serializable {


    @JsonProperty("menu")
    private List<MenuExtendDTO> menuList;


    public void setRoleDTO(RoleDTO roleDTO) {
        this.setId(roleDTO.getId());
        this.setDescription(roleDTO.getDescription());
        this.setDeactivationDate(roleDTO.getDeactivationDate());
    }

    @JsonIgnore
    public RoleDTO getRoleDTO() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(this.getId());
        roleDTO.setDescription(this.getDescription());
        roleDTO.setDeactivationDate(this.getDeactivationDate());
        return roleDTO;
    }
}
