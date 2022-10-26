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
        updateInstalmentPayment(paymentActionsnew);
        return paymentActionsnew;
    }

    public void updateInstalmentPayment(PaymentActions paymentActions) {

        Double ammount = paymentActions.getAmmount();
        Double capitalPaid = 0D;
        Double interestPaid = 0D;
        if (ammount > 0) {
            List<InstalmentPayments> instalmentList = instalmentPaymentsRepository.getPaidInstalmentPaymentId(paymentActions.getLoan().getId());
            List<InstalmentPayments> saveInstalment = new ArrayList<>();
            Long size = Long.valueOf(instalmentList.size());
            Long index = 1L;
            for (InstalmentPayments instalmentPayments : instalmentList) {
                Double remainingCapital = instalmentPayments.getRemainingCapital();
                Double remainingInterest = instalmentPayments.getRemainingInterest();
                if (ammount <= remainingInterest) {
                    Double newRemainingInterest = remainingInterest - ammount;
                    interestPaid = interestPaid + ammount;
                    instalmentPayments.setRemainingInterest(newRemainingInterest);
                    ammount = 0D;
                    // saveIntalment.add(instalmentPayments);
                    break;
                } else if (ammount > remainingInterest && ammount <= remainingInterest + remainingCapital) {
                    Double newRemainingInterest = 0D;
                    ammount = ammount - remainingInterest;
                    Double newRemainingCapital = remainingCapital - ammount;
                    interestPaid = interestPaid + remainingInterest;
                    capitalPaid = capitalPaid + ammount;
                    instalmentPayments.setRemainingInterest(newRemainingInterest);
                    instalmentPayments.setRemainingCapital(newRemainingCapital);
                    ammount = 0D;
                    //saveInstalment.add(instalmentPayments);
                    break;
                } else {
                    Double newRemainingCapital;
                    Double newRemainingInterest;
                    Double total = remainingCapital + remainingInterest;
                    if (index.equals(size) && ammount > total) {
                        ammount = ammount - (remainingCapital + remainingInterest);
                        capitalPaid = capitalPaid + remainingCapital;
                        interestPaid = interestPaid + remainingInterest;
                        newRemainingInterest = 0D;
                        instalmentPayments.setRemainingInterest(newRemainingInterest);
                        newRemainingCapital = 0D - ammount;
                        instalmentPayments.setRemainingCapital(newRemainingCapital);
                    } else {
                        ammount = ammount - (remainingCapital + remainingInterest);
                        capitalPaid = capitalPaid + remainingCapital;
                        interestPaid = interestPaid + remainingInterest;
                        newRemainingCapital = 0D;
                        instalmentPayments.setRemainingCapital(newRemainingCapital);
                        newRemainingInterest = 0D;
                        instalmentPayments.setRemainingInterest(newRemainingInterest);
                        //saveInstalment.add(instalmentPayments);
                        index++;
                    }
                }
            }
            createInstalmentAction(paymentActions, capitalPaid, interestPaid);
            //instalmentPaymentsRepository.saveAll(saveInstalment);
        }
    }


    public InstalmentActions createInstalmentAction(PaymentActions paymentActions, Double capital, Double interest) {
        InstalmentActions instalmentActions = new InstalmentActions();
        instalmentActions.setCapitalAmmount(capital);
        instalmentActions.setInterestAmmount(interest);
        instalmentActions.setPaymentActions(paymentActions);
        return instalmentActionsRepository.save(instalmentActions);
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
        if (paymentActionExists.isPresent()) {
            PaymentActions paymentActions = paymentActionExists.get();
            List<InstalmentPayments> paymentsList = instalmentPaymentsRepository.getInstalmentPayments(paymentActions.getLoan().getId());
            List<InstalmentPayments> newInstalments = new ArrayList<>();
            InstalmentActions instalmentActionId = instalmentActionsRepository.getInstalmentActionsIds(paymentActionId);
            Double ammount = paymentActions.getAmmount();
            for (InstalmentPayments instalmentPayments : paymentsList) {
                Double instalmentInterest = instalmentPayments.getInterest();
                Double instalmentCapital = instalmentPayments.getCapital();
                Double remainingInterest = instalmentPayments.getRemainingInterest();
                Double remainingCapital = instalmentPayments.getRemainingCapital();
                if (ammount < instalmentCapital - remainingCapital) {
                    remainingCapital = remainingCapital + ammount;
                    instalmentPayments.setRemainingCapital(remainingCapital);
                    newInstalments.add(instalmentPayments);
                    break;
                } else if (ammount >= instalmentCapital - remainingCapital
                        && ammount <= (instalmentCapital - remainingCapital) + (instalmentInterest - remainingInterest)) {
                    ammount = ammount - (instalmentCapital - remainingCapital);
                    instalmentPayments.setRemainingCapital(instalmentCapital);
                    remainingInterest = remainingInterest + ammount;
                    instalmentPayments.setRemainingInterest(remainingInterest);
                    newInstalments.add(instalmentPayments);
                    break;
                } else {
                    ammount = ammount - ((instalmentInterest - remainingInterest) + (instalmentCapital - remainingCapital));
                    instalmentPayments.setRemainingInterest(instalmentInterest);
                    instalmentPayments.setRemainingCapital(instalmentCapital);
                    newInstalments.add(instalmentPayments);
                }
            }
            instalmentActionsRepository.deleteById(instalmentActionId.getId());
            instalmentPaymentsRepository.saveAll(newInstalments);
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
