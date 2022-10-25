package com.knowledge.spring.loan.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "instalment_actions")
public class InstalmentActions {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "capital_ammount")
    private double capitalAmmount;
    @Column(name = "interest_ammount")
    private double interestAmmount;
    @ManyToOne
    @JoinColumn(name = "payment", referencedColumnName = "id")
    private PaymentActions paymentActions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCapitalAmmount() {
        return capitalAmmount;
    }

    public void setCapitalAmmount(double capitalAmmount) {
        this.capitalAmmount = capitalAmmount;
    }

    public double getInterestAmmount() {
        return interestAmmount;
    }

    public void setInterestAmmount(double interestAmmount) {
        this.interestAmmount = interestAmmount;
    }

    public PaymentActions getPaymentActions() {
        return paymentActions;
    }

    public void setPaymentActions(PaymentActions paymentActions) {
        this.paymentActions = paymentActions;
    }
}
