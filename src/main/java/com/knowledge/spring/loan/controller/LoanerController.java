package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.Loaner;
import com.knowledge.spring.loan.service.LoanerService;
import com.knowledge.spring.loan.service.dto.LoanerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class LoanerController {

    private LoanerService loanerService;

    public LoanerController(LoanerService loanerService) {
        this.loanerService = loanerService;
    }

    @PostMapping("/loaner")
    public ResponseEntity<Loaner> saveLoaner(@RequestBody Loaner loaner) {
        return new ResponseEntity<Loaner>(loanerService.saveLoaner(loaner), HttpStatus.CREATED);
    }

    @GetMapping("/loaner")
    public List<Loaner> getAllLoaner() {
        return loanerService.getAllLoaner();
    }

    @GetMapping("/loaner/{loanerId}")
    public ResponseEntity<Loaner> getLoanerById(@PathVariable Long loanerId) {
        return new ResponseEntity<>(loanerService.getLoanerById(loanerId), HttpStatus.OK);
    }

    @DeleteMapping("loaner/{loanerId}")
    public void deleteLoaner(@PathVariable Long loanerId) {
        loanerService.deleteLoaner(loanerId);
    }

    @PutMapping("/loaner")
    public ResponseEntity<Loaner> updateLoaner(@RequestBody LoanerDTO loanerDTO) {
        return new ResponseEntity<>(loanerService.updateLoaner(loanerDTO), HttpStatus.OK);
    }
}
