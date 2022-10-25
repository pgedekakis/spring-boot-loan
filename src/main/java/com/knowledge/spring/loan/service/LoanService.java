package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.Bank;
import com.knowledge.spring.loan.model.InstalmentPayments;
import com.knowledge.spring.loan.model.Loan;
import com.knowledge.spring.loan.model.Loaner;
import com.knowledge.spring.loan.repository.BankRepository;
import com.knowledge.spring.loan.repository.InstalmentPaymentsRepository;
import com.knowledge.spring.loan.repository.LoanRepository;
import com.knowledge.spring.loan.repository.LoanerRepository;
import com.knowledge.spring.loan.service.dto.LoanDTO;
import com.knowledge.spring.loan.service.dto.LoanExtendDTO;
import com.knowledge.spring.loan.service.mapper.LoanMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;


@Log4j2
@Service
public class LoanService {

    private LoanRepository loanRepository;
    private LoanMapper loanMapper;
    private InstalmentPaymentsRepository instalmentPaymentsRepository;
    private LoanerRepository loanerRepository;
    private BankRepository bankRepository;


    public LoanService(LoanRepository loanRepository, LoanMapper loanMapper,
                       InstalmentPaymentsRepository instalmentPaymentsRepository, LoanerRepository loanerRepository, BankRepository bankRepository) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.instalmentPaymentsRepository = instalmentPaymentsRepository;
        this.loanerRepository = loanerRepository;
        this.bankRepository = bankRepository;
    }

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoan() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan doesn't exist with this id :" + loanId));
        return loan;
    }

    public void deleteLoan(Long loanId) {
        Optional<Loan> loanExists = loanRepository.findById(loanId);
        List<Long> paymentsIds = instalmentPaymentsRepository.getInstalmentPaymentsIds(loanId);
        if (loanExists.isPresent()) {
            if (!paymentsIds.isEmpty()) {
                instalmentPaymentsRepository.deleteAllById(paymentsIds);
            }
            loanRepository.deleteById(loanId);
        } else {
            log.info("Loan does not exist with this id");
        }
    }

    public Loan updateLoan(LoanDTO loanDTO) {
        Optional<Loan> loanExists = loanRepository.findById(loanDTO.getId());
        Loan loan = loanMapper.toEntity(loanDTO);
        if (loanExists.isPresent()) {
            loanRepository.save(loan);
        } else {
            log.info("Cannot update non existent loan");
        }
        return loan;
    }

    public LoanExtendDTO createLoan(LoanExtendDTO loanExtendDTO) {
        Loan loan = loanMapper.toEntity(loanExtendDTO.getLoanDTO());
        Loan newLoan = loanRepository.save(loan);
        createPayment(loanExtendDTO, newLoan);
        return loanExtendDTO;
    }

    public LoanExtendDTO createPayment(LoanExtendDTO loanExtendDTO, Loan loan) {
        Long instalment = loanExtendDTO.getInstalment();
        Loaner loaner = loanExtendDTO.getLoaner();
        Long newCapital = loanExtendDTO.getStartAmmount() / instalment;
        Long newInsterest = loanExtendDTO.getStartInterest() / instalment;
        List<InstalmentPayments> instalmentPaymentsList = setInstalmentPayments(loan, instalment, loaner, newCapital, newInsterest);
        instalmentPaymentsRepository.saveAll(instalmentPaymentsList);
        return loanExtendDTO;
    }

    private static List<InstalmentPayments> setInstalmentPayments(Loan loan, Long instalment, Loaner loaner, Long newCapital, Long newInsterest) {
        List<InstalmentPayments> instalmentPaymentsList = new ArrayList<>();
        for (int i = 0; i < instalment; i++) {
            InstalmentPayments instalmentPayments = new InstalmentPayments();
            instalmentPayments.setCapital(Math.toIntExact(newCapital));
            instalmentPayments.setRemainingCapital(Math.toIntExact(newCapital));
            instalmentPayments.setInterest(Math.toIntExact(newInsterest));
            instalmentPayments.setRemainingInterest(Math.toIntExact(newInsterest));
            instalmentPayments.setLoan(loan);
            instalmentPayments.setLoaner(loaner);
            instalmentPaymentsList.add(instalmentPayments);
        }
        return instalmentPaymentsList;
    }
}
