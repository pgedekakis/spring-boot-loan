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
    private double capital;
    @Column(name = "remaining_capital")
    private double remainingCapital;
    @Column(name = "interest")
    private double interest;
    @Column(name = "remaining_interest")
    private double remainingInterest;

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

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getRemainingCapital() {
        return remainingCapital;
    }

    public void setRemainingCapital(double remainingCapital) {
        this.remainingCapital = remainingCapital;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public double getRemainingInterest() {
        return remainingInterest;
    }

    public void setRemainingInterest(double remainingInterest) {
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
