package com.knowledge.spring.loan.service.mapper;


import com.knowledge.spring.loan.model.InstalmentActions;
import com.knowledge.spring.loan.service.dto.InstalmentActionsDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class InstalmentActionMapper {
    @Autowired
    private ModelMapper modelMapper;

    public InstalmentActionsDTO toDTO(InstalmentActions instalmentActions) {
        return modelMapper.map(instalmentActions, InstalmentActionsDTO.class);
    }

    public InstalmentActions toEntity(InstalmentActionsDTO instalmentActionsDTO) {
        return modelMapper.map(instalmentActionsDTO, InstalmentActions.class);
    }
}
