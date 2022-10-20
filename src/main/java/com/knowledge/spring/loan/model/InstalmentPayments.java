package com.knowledge.spring.loan.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "instalment_payments")
public class InstalmentPayments {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "capital")
    private int capital;
    @Column(name = "remaining_capital")
    private int remainingCapital;
    @Column(name = "interest")
    private int interest;
    @Column(name = "remaining_interest")
    private int remainingInterest;

    @ManyToOne
    @JoinColumn(name = "loan", referencedColumnName = "id")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "loaner", referencedColumnName = "id")
    private Loaner loaner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public int getRemainingCapital() {
        return remainingCapital;
    }

    public void setRemainingCapital(int remainingCapital) {
        this.remainingCapital = remainingCapital;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getRemainingInterest() {
        return remainingInterest;
    }

    public void setRemainingInterest(int remainingInterest) {
        this.remainingInterest = remainingInterest;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Loaner getLoaner() {
        return loaner;
    }

    public void setLoaner(Loaner loaner) {
        this.loaner = loaner;
    }


}
