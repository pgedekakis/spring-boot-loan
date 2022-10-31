package com.knowledge.spring.loan.service.mapper;

import com.knowledge.spring.loan.model.MenuEntityRole;
import com.knowledge.spring.loan.service.dto.MenuEntityRoleDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class MenuEntityRoleMapper {
    @Autowired
    private ModelMapper modelMapper;

    public MenuEntityRoleDTO toDTO(MenuEntityRole menuEntityRole){
        return modelMapper.map(menuEntityRole, MenuEntityRoleDTO.class);
    }
    public MenuEntityRole toEntity(MenuEntityRoleDTO menuEntityRoleDTO){
        return modelMapper.map(menuEntityRoleDTO, MenuEntityRole.class);
    }
}
