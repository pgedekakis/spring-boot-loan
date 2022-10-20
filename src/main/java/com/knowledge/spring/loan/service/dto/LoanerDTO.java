package com.knowledge.spring.loan.service.dto;

import com.knowledge.spring.loan.model.Loaner;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class LoanerDTO {
    private long id;
    private String loanerName;
    private String loanerSurname;
    private String fatherName;
    private Date birthdate;
    private long afm;

    public LoanerDTO(Loaner loaner) {
        this.setId(loaner.getId());
        this.setLoanerName(loaner.getLoanerName());
        this.setLoanerSurname(loaner.getLoanerSurname());
        this.setFatherName(loaner.getFatherName());
        this.setBirthdate(loaner.getBirthdate());
        this.setAfm(loaner.getAfm());
    }
}
