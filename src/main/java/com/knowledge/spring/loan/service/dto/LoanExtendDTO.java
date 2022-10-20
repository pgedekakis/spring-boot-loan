package com.knowledge.spring.loan.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.knowledge.spring.loan.model.Loan;
import com.knowledge.spring.loan.model.Loaner;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@Jacksonized
public class LoanExtendDTO extends LoanDTO implements Serializable {

    @JsonProperty("instalment")
    private Long instalment;

    @JsonProperty("loaner")
    private Loaner loaner;

    public void setLoanDTO(LoanDTO loanDTO) {
        this.setId(loanDTO.getId());
        this.setStartAmmount(loanDTO.getStartAmmount());
        this.setStartInterest(loanDTO.getStartInterest());
        this.setBank(loanDTO.getBank());
        this.setLoanType(loanDTO.getLoanType());
    }

    @JsonIgnore
    public LoanDTO getLoanDTO() {
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setStartAmmount(this.getStartAmmount());
        loanDTO.setLoanType(this.getLoanType());
        loanDTO.setBank(this.getBank());
        loanDTO.setStartInterest(this.getStartInterest());
        return loanDTO;
    }

}
