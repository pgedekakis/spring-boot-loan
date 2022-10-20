package com.knowledge.spring.loan.service.mapper;

import com.knowledge.spring.loan.model.Bank;
import com.knowledge.spring.loan.model.LoanType;
import com.knowledge.spring.loan.service.dto.BankDTO;
import com.knowledge.spring.loan.service.dto.LoanTypeDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@Component
public class LoanTypeMapper {

    @Autowired
    private ModelMapper modelMapper;

    public LoanTypeDTO toDTO(LoanType loanType) {
        return modelMapper.map(loanType, LoanTypeDTO.class);
    }

    public LoanType toEntity(LoanTypeDTO loanTypeDTO) {
        return modelMapper.map(loanTypeDTO, LoanType.class);
    }
}
