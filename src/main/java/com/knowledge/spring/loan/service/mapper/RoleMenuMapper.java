package com.knowledge.spring.loan.service.mapper;

import com.knowledge.spring.loan.model.RoleMenu;
import com.knowledge.spring.loan.service.dto.RoleMenuDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class RoleMenuMapper {
    @Autowired
    private ModelMapper modelMapper;

    public RoleMenuDTO toDTO(RoleMenu roleMenu){
        return modelMapper.map(roleMenu, RoleMenuDTO.class);
    }

    public RoleMenu toEntity(RoleMenuDTO roleMenuDTO){
        return modelMapper.map(roleMenuDTO, RoleMenu.class);
    }
}
