package com.knowledge.spring.loan.service.mapper;


import com.knowledge.spring.loan.model.Role;
import com.knowledge.spring.loan.service.dto.RoleDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class RoleMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO toDTO(Role role){
        return modelMapper.map(role, RoleDTO.class);
    }
    public Role toEntity(RoleDTO roleDTO){
        return modelMapper.map(roleDTO, Role.class);
    }
}
