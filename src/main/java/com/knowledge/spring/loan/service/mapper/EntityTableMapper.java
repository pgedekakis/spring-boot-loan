package com.knowledge.spring.loan.service.mapper;


import com.knowledge.spring.loan.model.EntityTable;
import com.knowledge.spring.loan.service.dto.EntityTableDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class EntityTableMapper {

    @Autowired
    private ModelMapper modelMapper;

    public EntityTableDTO toDTO(EntityTable entityTable){
        return modelMapper.map(entityTable, EntityTableDTO.class);
    }
    public EntityTable toEntity(EntityTableDTO entityTableDTO){
        return modelMapper.map(entityTableDTO, EntityTable.class);
    }
}
