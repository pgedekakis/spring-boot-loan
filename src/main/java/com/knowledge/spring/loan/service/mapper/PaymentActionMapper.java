package com.knowledge.spring.loan.service.mapper;


import com.knowledge.spring.loan.model.PaymentActions;
import com.knowledge.spring.loan.service.dto.PaymentActionDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class PaymentActionMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PaymentActionDTO toDTO(PaymentActions paymentActions) {
        return modelMapper.map(paymentActions, PaymentActionDTO.class);
    }

    public PaymentActions toEntity(PaymentActionDTO paymentActionDTO) {
        return modelMapper.map(paymentActionDTO, PaymentActions.class);
    }
}
