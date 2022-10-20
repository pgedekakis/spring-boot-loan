package com.knowledge.spring.loan.service.dto;


import com.knowledge.spring.loan.model.Bank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BankDTO {
    private long id;
    private String bankName;

    public BankDTO(Bank bank) {
        this.setId(bank.getId());
        this.setBankName(bank.getBankName());
    }
}
