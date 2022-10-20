package com.knowledge.spring.loan.service.mapper;

import com.knowledge.spring.loan.model.Bank;
import com.knowledge.spring.loan.service.dto.BankDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class BankMapper {
    @Autowired
    private ModelMapper modelMapper;

    public BankDTO toDTO(Bank bank) {
        return modelMapper.map(bank, BankDTO.class);
    }

    public Bank toEntity(BankDTO bankDTO) {
        return modelMapper.map(bankDTO, Bank.class);
    }
}
