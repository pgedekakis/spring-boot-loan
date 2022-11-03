package com.knowledge.spring.loan.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.knowledge.spring.loan.service.dto.EntityTableDTO;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@Jacksonized
public class EntityExtendDTO extends EntityTableDTO implements Serializable {



    public void setEntityDTO(EntityTableDTO entityTableDTO) {
        this.setId(entityTableDTO.getId());
        this.setJoinName(entityTableDTO.getJoinName());
        this.setDeactivationDate(entityTableDTO.getDeactivationDate());
    }

    @JsonIgnore
    public EntityTableDTO getEntityDto() {
        EntityTableDTO entityExtendDTO = new EntityExtendDTO();
        entityExtendDTO.setId(this.getId());
        entityExtendDTO.setJoinName(this.getJoinName());
        entityExtendDTO.setDeactivationDate(this.getDeactivationDate());
        return entityExtendDTO;
    }
}
