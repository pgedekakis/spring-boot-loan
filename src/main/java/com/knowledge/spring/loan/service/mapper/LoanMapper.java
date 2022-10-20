package com.knowledge.spring.loan.service.mapper;

import com.knowledge.spring.loan.model.Bank;
import com.knowledge.spring.loan.model.Loan;
import com.knowledge.spring.loan.service.dto.BankDTO;
import com.knowledge.spring.loan.service.dto.LoanDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoanMapper {
    @Autowired
    private ModelMapper modelMapper;

    public LoanDTO toDTO(Loan loan) {
        return modelMapper.map(loan, LoanDTO.class);
    }

    public Loan toEntity(LoanDTO loanDTO) {
        return modelMapper.map(loanDTO, Loan.class);
    }
}
