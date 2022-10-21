package com.knowledge.spring.loan.service;

import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.InstalmentActions;
import com.knowledge.spring.loan.model.InstalmentPayments;
import com.knowledge.spring.loan.model.PaymentActions;
import com.knowledge.spring.loan.repository.InstalmentActionsRepository;
import com.knowledge.spring.loan.repository.InstalmentPaymentsRepository;
import com.knowledge.spring.loan.repository.PaymentActionsRepository;
import com.knowledge.spring.loan.service.dto.PaymentActionDTO;
import com.knowledge.spring.loan.service.mapper.PaymentActionMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PaymentActionsService {

    private PaymentActionsRepository paymentActionsRepository;
    private PaymentActionMapper paymentActionMapper;
    private InstalmentActionsRepository instalmentActionsRepository;
    private InstalmentPaymentsRepository instalmentPaymentsRepository;

    public PaymentActionsService(PaymentActionsRepository paymentActionsRepository, PaymentActionMapper paymentActionMapper,
                                 InstalmentActionsRepository instalmentActionsRepository, InstalmentPaymentsRepository instalmentPaymentsRepository) {
        this.paymentActionsRepository = paymentActionsRepository;
        this.paymentActionMapper = paymentActionMapper;
        this.instalmentActionsRepository = instalmentActionsRepository;
        this.instalmentPaymentsRepository = instalmentPaymentsRepository;
    }

    public PaymentActions savePaymentAction(PaymentActions paymentActions) {
        PaymentActions paymentActionsnew = paymentActionsRepository.save(paymentActions);
        updateInstalmentPayment(paymentActions);
        return paymentActionsnew;
    }

    public void updateInstalmentPayment(PaymentActions paymentActions) {
        Long ammount = paymentActions.getAmmount();
        Long capitalPaid = 0L;
        Long interestPaid = 0L;
        if (ammount > 0) {
            List<InstalmentPayments> paidInstalmentList = instalmentPaymentsRepository.getPaidInstalmentPaymentId(paymentActions.getLoan().getId());
            List<InstalmentPayments> saveIntalment = new ArrayList<>();
            for (InstalmentPayments instalmentPayments : paidInstalmentList) {
                Long remainingCapital = Long.valueOf(instalmentPayments.getRemainingCapital());
                Long remainingInterest = Long.valueOf(instalmentPayments.getRemainingInterest());
                if (ammount <= remainingCapital) {
                    Long newRemainingCapital = remainingCapital - ammount;
                    capitalPaid = capitalPaid + ammount;
                    instalmentPayments.setRemainingCapital(Math.toIntExact(newRemainingCapital));
                    ammount = 0L;
                    saveIntalment.add(instalmentPayments);
                    break;
                } else if (ammount > remainingCapital && ammount <= remainingCapital + remainingInterest) {
                    Long newRemainingCapital = 0L;
                    ammount = ammount - remainingCapital;
                    Long newRemainingInterest = remainingInterest - ammount;
                    capitalPaid = capitalPaid + remainingCapital;
                    interestPaid = interestPaid + ammount;
                    instalmentPayments.setRemainingCapital(Math.toIntExact(newRemainingCapital));
                    instalmentPayments.setRemainingInterest(Math.toIntExact(newRemainingInterest));
                    ammount = 0L;
                    saveIntalment.add(instalmentPayments);
                    break;
                } else {
                    ammount = ammount - (remainingCapital + remainingInterest);
                    capitalPaid = capitalPaid + remainingCapital;
                    interestPaid = interestPaid + remainingInterest;
                    Long newRemainingCapital = 0L;
                    instalmentPayments.setRemainingCapital(Math.toIntExact(newRemainingCapital));
                    Long newRemainingInterest = 0L;
                    instalmentPayments.setRemainingInterest(Math.toIntExact(newRemainingInterest));
                    saveIntalment.add(instalmentPayments);
                }
            }
            createInstalmentAction(paymentActions, capitalPaid, interestPaid);
            //instalmentPaymentsRepository.saveAll(saveIntalment);
            if (ammount > 0) {
                log.info("Ammount to be restored:{}", ammount);
            }
        }
    }


    public void createInstalmentAction(PaymentActions paymentActions, Long capital, Long interest) {
        InstalmentActions instalmentActions = new InstalmentActions();
        instalmentActions.setCapitalAmmount(capital);
        instalmentActions.setInterestAmmount(interest);
        instalmentActions.setPaymentActions(paymentActions);
        instalmentActionsRepository.save(instalmentActions);
    }


    public List<PaymentActions> getAllPaymentActions() {
        return paymentActionsRepository.findAll();
    }

    public PaymentActions getPaymentActionsById(Long paymentActionsId) {
        PaymentActions paymentActions = paymentActionsRepository.findById(paymentActionsId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Action doesn't exist with this id :" + paymentActionsId));
        return paymentActions;
    }

    public void deletePaymentAction(Long paymentActionId) {
        Optional<PaymentActions> paymentActionExists = paymentActionsRepository.findById(paymentActionId);
        List<Long> workingCapitalIds = paymentActionsRepository.getInstalmentActionsIds(paymentActionId);
        if (paymentActionExists.isPresent()) {
            if (!workingCapitalIds.isEmpty()) {
                instalmentActionsRepository.deleteAllById(workingCapitalIds);
            }
            paymentActionsRepository.deleteById(paymentActionId);
        } else {
            log.info("Payment does not exist with this id");
        }
    }

    public PaymentActions updatePaymentAction(PaymentActionDTO paymentActionDTO) {
        Optional<PaymentActions> paymentActionExists = paymentActionsRepository.findById(paymentActionDTO.getId());
        PaymentActions paymentActions = paymentActionMapper.toEntity(paymentActionDTO);
        if (paymentActionExists.isPresent()) {
            paymentActionsRepository.save(paymentActions);
        } else {
            log.info("Cannot update non existent action");
        }


        return paymentActions;
    }
}
