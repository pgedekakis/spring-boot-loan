package com.knowledge.spring.loan.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@Table(name = "loan")
public class Loan {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "bank", referencedColumnName = "id")
    private Bank bank;
    @Column(name = "start_ammount")
    private int startAmmount;
    @Column(name = "start_interest")
    private int startInterest;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private LoanType loanType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStartAmmount() {
        return startAmmount;
    }

    public void setStartAmmount(int startAmmount) {
        this.startAmmount = startAmmount;
    }

    public int getStartInterest() {
        return startInterest;
    }

    public void setStartInterest(int startInterest) {
        this.startInterest = startInterest;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}
