package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.LoanType;
import com.knowledge.spring.loan.service.LoanTypeService;
import com.knowledge.spring.loan.service.dto.LoanTypeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class LoanTypeController {

    private LoanTypeService loanTypeService;

    public LoanTypeController(LoanTypeService loanTypeService) {
        this.loanTypeService = loanTypeService;
    }

    @PostMapping("/loan-type")
    public ResponseEntity<LoanType> saveLoan(@RequestBody LoanType loanType) {
        return new ResponseEntity<LoanType>(loanTypeService.saveLoanType(loanType), HttpStatus.CREATED);
    }

    @GetMapping("/loan-type")
    public List<LoanType> getAllLoanType() {
        return loanTypeService.getAllLoanType();
    }

    @GetMapping("/loan-type/{loanTypeId}")
    public ResponseEntity<LoanType> getLoanTypeById(@PathVariable Long loanTypeId) {
        return new ResponseEntity<>(loanTypeService.getLoanTypeById(loanTypeId), HttpStatus.OK);
    }

    @DeleteMapping("/loan-type/{loanTypeId}")
    public void deleteLoanType(@PathVariable Long loanTypeId) {
        loanTypeService.deleteLoanType(loanTypeId);
    }

    @PutMapping("/loan-type")
    public ResponseEntity<LoanType> updateLoanType(@RequestBody LoanTypeDTO loanTypeDTO) {
        return new ResponseEntity<>(loanTypeService.updateLoanType(loanTypeDTO), HttpStatus.OK);
    }
}
