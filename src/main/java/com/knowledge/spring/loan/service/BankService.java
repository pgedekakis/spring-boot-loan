package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.Bank;
import com.knowledge.spring.loan.repository.BankRepository;
import com.knowledge.spring.loan.repository.LoanRepository;
import com.knowledge.spring.loan.service.dto.BankDTO;
import com.knowledge.spring.loan.service.mapper.BankMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class BankService {

    private BankRepository bankRepository;
    private BankMapper bankMapper;

    private LoanRepository loanRepository;

    public BankService(BankRepository bankRepository, BankMapper bankMapper, LoanRepository loanRepository) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
        this.loanRepository = loanRepository;

    }

    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public List<Bank> getAllBank() {
        return bankRepository.findAll();
    }

    public Bank getBankById(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new ResourceNotFoundException("Bank doesn't exist with this id :" + bankId));
        return bank;
    }

    public void deleteBank(Long bankId) {
        Optional<Bank> bankExists = bankRepository.findById(bankId);
        List<Long> loanIds = loanRepository.getLoanIdsByBank(bankId);
        if (bankExists.isPresent()) {
            if (!loanIds.isEmpty()) {
                loanRepository.deleteAllById(loanIds);
            }
            bankRepository.deleteById(bankId);
        } else {
            log.info("Bank does not exist with this id");
        }
    }

    public Bank updateBank(BankDTO bankDTO) {
        Optional<Bank> bankExists = bankRepository.findById(bankDTO.getId());
        Bank bank = bankMapper.toEntity(bankDTO);
        if (bankExists.isPresent()) {
            //bankRepository.deleteById(bankDTO.getId());
            bankRepository.save(bank);
        } else {
            log.info("Cannot update non existent bank");
        }


        return bank;
    }


}
