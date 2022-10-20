package com.knowledge.spring.loan.service.dto;

import com.knowledge.spring.loan.model.LoanType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanTypeDTO {
    private long id;
    private String loanTypeName;

    public LoanTypeDTO(LoanType loanType) {
        this.setId(loanType.getId());
        this.setLoanTypeName(loanType.getLoanTypeName());
    }
}
