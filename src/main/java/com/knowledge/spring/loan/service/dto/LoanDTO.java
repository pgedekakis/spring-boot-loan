package com.knowledge.spring.loan.service.dto;

import com.knowledge.spring.loan.model.Bank;
import com.knowledge.spring.loan.model.Loan;
import com.knowledge.spring.loan.model.LoanType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanDTO {
    private long id;
    private int startAmmount;
    private int startInterest;
    private Bank bank;
    private LoanType loanType;


    public LoanDTO(Loan loan) {
        this.setId(loan.getId());
        this.setStartAmmount(loan.getStartAmmount());
        this.setStartInterest(loan.getStartInterest());
        this.setBank(loan.getBank());
        this.setLoanType(loan.getLoanType());
    }
}
