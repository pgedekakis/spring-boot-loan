package com.knowledge.spring.loan.service.dto;

import com.knowledge.spring.loan.model.PaymentActions;
import com.knowledge.spring.loan.model.InstalmentActions;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstalmentActionsDTO {
    private long id;
    private long capitalAmmount;
    private long interestAmmount;
    private PaymentActions paymentActions;

    public InstalmentActionsDTO(InstalmentActions instalmentActions) {
        this.setId(instalmentActions.getId());
        this.setCapitalAmmount(instalmentActions.getCapitalAmmount());
        this.setInterestAmmount(instalmentActions.getInterestAmmount());
        this.setPaymentActions(instalmentActions.getPaymentActions());
    }
}
