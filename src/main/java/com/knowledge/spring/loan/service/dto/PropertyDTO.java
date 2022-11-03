package com.knowledge.spring.loan.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Jacksonized
@Data
public class PropertyDTO extends MenuEntityRoleDTO implements Serializable {

    @JsonProperty("insert")
    private Boolean insert;

    @JsonProperty("update")
    private Boolean update;

    @JsonProperty("delete")
    private Boolean delete;

    public void setPropertyDTO(MenuEntityRoleDTO menuEntityRoleDTO) {
        this.setId(menuEntityRoleDTO.getId());
        this.setInsert(menuEntityRoleDTO.isInsert());
        this.setUpdate(menuEntityRoleDTO.isUpdate());
        this.setDelete(menuEntityRoleDTO.isDelete());
    }

    @JsonIgnore
    public MenuEntityRoleDTO getMenuEntityRoleDTO() {
        MenuEntityRoleDTO menuEntityRoleDTO = new MenuEntityRoleDTO();
        menuEntityRoleDTO.setId(this.getId());
        menuEntityRoleDTO.setInsert(this.getInsert());
        menuEntityRoleDTO.setUpdate(this.getUpdate());
        menuEntityRoleDTO.setDelete(this.getDelete());
        return menuEntityRoleDTO;
    }
}

