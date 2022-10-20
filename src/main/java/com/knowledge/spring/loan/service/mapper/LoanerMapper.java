package com.knowledge.spring.loan.service.mapper;


import com.knowledge.spring.loan.model.Loaner;
import com.knowledge.spring.loan.service.dto.LoanerDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoanerMapper {

    @Autowired
    private ModelMapper modelMapper;

    public LoanerDTO toDTO(Loaner loaner) {
        return modelMapper.map(loaner, LoanerDTO.class);
    }

    public Loaner toEntity(LoanerDTO loanerDTO) {
        return modelMapper.map(loanerDTO, Loaner.class);
    }
}
