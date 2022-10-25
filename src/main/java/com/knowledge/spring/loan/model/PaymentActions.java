package com.knowledge.spring.loan.model;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "payment_actions")
public class PaymentActions {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ammount")
    private double ammount;
    @ManyToOne
    @JoinColumn(name = "loan", referencedColumnName = "id")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "loaner_id", referencedColumnName = "id")
    private Loaner loaner;


    public PaymentActions(long ammount, Loan loan, Loaner loaner) {
        this.ammount = ammount;
        this.loan = loan;
        this.loaner = loaner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(long ammount) {
        this.ammount = ammount;
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
