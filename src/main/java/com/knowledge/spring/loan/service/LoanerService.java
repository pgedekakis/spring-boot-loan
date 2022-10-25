package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.Loaner;
import com.knowledge.spring.loan.repository.InstalmentPaymentsRepository;
import com.knowledge.spring.loan.repository.LoanerRepository;
import com.knowledge.spring.loan.repository.PaymentActionsRepository;
import com.knowledge.spring.loan.service.dto.LoanerDTO;
import com.knowledge.spring.loan.service.mapper.LoanerMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class LoanerService {

    private LoanerRepository loanerRepository;

    private LoanerMapper loanerMapper;
    private PaymentActionsRepository paymentActionsRepository;
    private InstalmentPaymentsRepository instalmentPaymentsRepository;

    public LoanerService(LoanerRepository loanerRepository, LoanerMapper loanerMapper, PaymentActionsRepository paymentActionsRepository,
                         InstalmentPaymentsRepository instalmentPaymentsRepository) {
        this.loanerRepository = loanerRepository;
        this.loanerMapper = loanerMapper;
        this.paymentActionsRepository = paymentActionsRepository;
        this.instalmentPaymentsRepository = instalmentPaymentsRepository;
    }

    public Loaner saveLoaner(Loaner loaner) {
        return loanerRepository.save(loaner);
    }

    public List<Loaner> getAllLoaner() {
        return loanerRepository.findAll();
    }

    public Loaner getLoanerById(Long loanerId) {
        Loaner loaner = loanerRepository.findById(loanerId)
                .orElseThrow(() -> new ResourceNotFoundException("Loaner not found with id:" + loanerId));

        return loaner;
    }

    public void deleteLoaner(Long loanerId) {
        Optional<Loaner> loanerExist = loanerRepository.findById(loanerId);
        List<Long> workingIds = paymentActionsRepository.getWorkingIds(loanerId);
        List<Long> paymentsIds = instalmentPaymentsRepository.getPaymentsIds(loanerId);
        if (!workingIds.isEmpty()) {
            paymentActionsRepository.deleteAllById(workingIds);
        }
        if (!paymentsIds.isEmpty()) {
            instalmentPaymentsRepository.deleteAllById(paymentsIds);
        }
        if (loanerExist.isPresent()) {
            loanerRepository.deleteById(loanerId);
        } else {
            log.info("Cannot delete non existent loaner");
        }
    }

    public Loaner updateLoaner(LoanerDTO loanerDTO) {
        Optional<Loaner> loanerExist = loanerRepository.findById(loanerDTO.getId());
        Loaner loaner = loanerMapper.toEntity(loanerDTO);
        if (loanerExist.isPresent()) {
            loanerRepository.save(loaner);
        } else {
            log.info("Cannot update non existent loaner");
        }
        return loaner;
    }


}
