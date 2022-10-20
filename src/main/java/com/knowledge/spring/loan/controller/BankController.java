package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.Bank;
import com.knowledge.spring.loan.service.BankService;
import com.knowledge.spring.loan.service.dto.BankDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class BankController {

    private BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/bank")
    public ResponseEntity<Bank> saveBank(@RequestBody Bank bank) {
        return new ResponseEntity<Bank>(bankService.saveBank(bank), HttpStatus.CREATED);
    }

    @GetMapping("/bank")
    public List<Bank> getAllBank() {
        return bankService.getAllBank();
    }

    @GetMapping("/bank/{bankId}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long bankId) {
        return new ResponseEntity<>(bankService.getBankById(bankId), HttpStatus.OK);
    }

    @DeleteMapping("/bank/{bankId}")
    public void deleteBank(@PathVariable Long bankId) {
        bankService.deleteBank(bankId);
    }

    @PutMapping("/bank")
    public ResponseEntity<Bank> updateBank(@RequestBody BankDTO bankDTO) {
        return new ResponseEntity<>(bankService.updateBank(bankDTO), HttpStatus.OK);
    }
}
