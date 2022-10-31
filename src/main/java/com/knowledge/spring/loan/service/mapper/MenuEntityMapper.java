package com.knowledge.spring.loan.service.mapper;


import com.knowledge.spring.loan.model.MenuEntity;
import com.knowledge.spring.loan.service.dto.MenuEntityDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class MenuEntityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MenuEntityDTO toDTO(MenuEntity menuEntity){
        return modelMapper.map(menuEntity, MenuEntityDTO.class);
    }
    public MenuEntity toEntity(MenuEntityDTO menuEntityDTO){
        return modelMapper.map(menuEntityDTO, MenuEntity.class);
    }

}
