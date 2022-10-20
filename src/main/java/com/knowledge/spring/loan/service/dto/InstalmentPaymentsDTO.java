package com.knowledge.spring.loan.service.dto;


import com.knowledge.spring.loan.model.InstalmentPayments;
import com.knowledge.spring.loan.model.Loan;
import com.knowledge.spring.loan.model.Loaner;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstalmentPaymentsDTO {

    private long id;
    private int capital;
    private int remainingCapital;
    private int interest;
    private int remainingInterest;
    private Loan loan;
    private Loaner loaner;

    public InstalmentPaymentsDTO(InstalmentPayments instalmentPayments) {
        this.setId(instalmentPayments.getId());
        this.setCapital(instalmentPayments.getCapital());
        this.setRemainingCapital(instalmentPayments.getRemainingCapital());
        this.setInterest(instalmentPayments.getInterest());
        this.setRemainingInterest(instalmentPayments.getRemainingInterest());
        this.setLoan(instalmentPayments.getLoan());
        this.setLoaner(instalmentPayments.getLoaner());
    }
}
