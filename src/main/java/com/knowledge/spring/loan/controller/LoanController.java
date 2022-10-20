package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.Loan;
import com.knowledge.spring.loan.service.LoanService;
import com.knowledge.spring.loan.service.dto.LoanDTO;
import com.knowledge.spring.loan.service.dto.LoanExtendDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loan")
    public ResponseEntity<Loan> saveLoan(@RequestBody Loan loan) {
        return new ResponseEntity<Loan>(loanService.saveLoan(loan), HttpStatus.CREATED);
    }

    @GetMapping("/loan")
    public List<Loan> getAllLoan() {
        return loanService.getAllLoan();
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long loanId) {
        return new ResponseEntity<>(loanService.getLoanById(loanId), HttpStatus.OK);
    }

    @DeleteMapping("/loan/{loanId}")
    public void deleteLoan(@PathVariable Long loanId) {
        loanService.deleteLoan(loanId);
    }

    @PutMapping("/loan")
    public ResponseEntity<Loan> updateLoan(@RequestBody LoanDTO loanDTO) {
        return new ResponseEntity<>(loanService.updateLoan(loanDTO), HttpStatus.OK);
    }

    @PostMapping("/loan/create")
    public LoanExtendDTO crateLoan(@RequestBody LoanExtendDTO loanExtendDTO) {
        return loanService.createLoan(loanExtendDTO);
    }

}

