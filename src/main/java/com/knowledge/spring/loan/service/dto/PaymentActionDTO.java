package com.knowledge.spring.loan.service.dto;


import com.knowledge.spring.loan.model.Loan;
import com.knowledge.spring.loan.model.Loaner;
import com.knowledge.spring.loan.model.PaymentActions;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentActionDTO {
    private long id;
    private long ammount;
    private Loan loan;
    private Loaner loaner;

    public PaymentActionDTO(PaymentActions paymentActions) {
        this.setId(paymentActions.getId());
        this.setAmmount(paymentActions.getAmmount());
        this.setLoan(paymentActions.getLoan());
        this.setLoaner(paymentActions.getLoaner());
    }
}
