package com.knowledge.spring.loan.service.mapper;

import com.knowledge.spring.loan.model.Bank;
import com.knowledge.spring.loan.model.InstalmentPayments;
import com.knowledge.spring.loan.service.dto.BankDTO;
import com.knowledge.spring.loan.service.dto.InstalmentPaymentsDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@Component
public class InstalmentPaymentsMapper {

    @Autowired
    private ModelMapper modelMapper;

    public InstalmentPaymentsDTO toDTO(InstalmentPayments instalmentPayments) {
        return modelMapper.map(instalmentPayments, InstalmentPaymentsDTO.class);
    }

    public InstalmentPayments toEntity(InstalmentPaymentsDTO instalmentPaymentsDTO) {
        return modelMapper.map(instalmentPaymentsDTO, InstalmentPayments.class);
    }
}
