package com.knowledge.spring.loan.service.mapper;


import com.knowledge.spring.loan.model.Menu;
import com.knowledge.spring.loan.service.dto.MenuDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class MenuMapper {
    @Autowired
    private ModelMapper modelMapper;

    public MenuDTO toDTO(Menu menu) {
        return modelMapper.map(menu, MenuDTO.class);
    }


    public Menu toEntity(MenuDTO menuDTO){return modelMapper.map(menuDTO,Menu.class);}
}