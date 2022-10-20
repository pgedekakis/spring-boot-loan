package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.Loan;
import com.knowledge.spring.loan.model.LoanType;
import com.knowledge.spring.loan.repository.LoanRepository;
import com.knowledge.spring.loan.repository.LoanTypeRepository;
import com.knowledge.spring.loan.service.dto.LoanTypeDTO;
import com.knowledge.spring.loan.service.mapper.LoanTypeMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class LoanTypeService {

    private LoanTypeRepository loanTypeRepository;
    private LoanTypeMapper loanTypeMapper;
    private LoanRepository loanRepository;

    public LoanTypeService(LoanTypeRepository loanTypeRepository, LoanTypeMapper loanTypeMapper, LoanRepository loanRepository) {
        this.loanTypeRepository = loanTypeRepository;
        this.loanTypeMapper = loanTypeMapper;
        this.loanRepository = loanRepository;

    }

    public LoanType saveLoanType(LoanType loanType) {
        return loanTypeRepository.save(loanType);
    }

    public List<LoanType> getAllLoanType() {
        return loanTypeRepository.findAll();
    }

    public LoanType getLoanTypeById(Long loanTypeId) {
        LoanType loanType = loanTypeRepository.findById(loanTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanType doesn't exist with this id :" + loanTypeId));
        return loanType;
    }

    public void deleteLoanType(Long loanTypeId) {
        Optional<LoanType> loanTypeExists = loanTypeRepository.findById(loanTypeId);
        List<Long> getLoanIds = loanTypeRepository.getLoanIds(loanTypeId);
        if (loanTypeExists.isPresent()) {
            if (!getLoanIds.isEmpty()) {
                loanRepository.deleteAllById(getLoanIds);
            }
            loanTypeRepository.deleteById(loanTypeId);
        } else {
            log.info("LoanType does not exist with this id");
        }
    }

    public LoanType updateLoanType(LoanTypeDTO loanTypeDTO) {
        Optional<LoanType> loanTypeExists = loanTypeRepository.findById(loanTypeDTO.getId());
        LoanType loanType = loanTypeMapper.toEntity(loanTypeDTO);
        if (loanTypeExists.isPresent()) {
            loanTypeRepository.save(loanType);
        } else {
            log.info("Cannot update non existent loan type");
        }
        return loanType;
    }
}
