package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.InstalmentPayments;
import com.knowledge.spring.loan.service.InstalmentPaymentsService;
import com.knowledge.spring.loan.service.dto.InstalmentPaymentsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class InstalmentPaymentsController {

    private InstalmentPaymentsService instalmentPaymentsService;

    public InstalmentPaymentsController(InstalmentPaymentsService instalmentPaymentsService) {
        this.instalmentPaymentsService = instalmentPaymentsService;
    }

    @PostMapping("/payments")
    public ResponseEntity<InstalmentPayments> saveInstalmentPayment(@RequestBody InstalmentPayments instalmentPayments) {
        return new ResponseEntity<InstalmentPayments>(instalmentPaymentsService.saveInstalmentPayments(instalmentPayments), HttpStatus.CREATED);
    }

    @GetMapping("/payments")
    public List<InstalmentPayments> getAllInstalmentPayments() {
        return instalmentPaymentsService.getAllInstalmentPayments();
    }

    @GetMapping("/payments/{instalmentPaymentId}")
    public ResponseEntity<InstalmentPayments> getInstalmentPaymentById(@PathVariable Long instalmentPaymentId) {
        return new ResponseEntity<>(instalmentPaymentsService.getInstalmentPaymentById(instalmentPaymentId), HttpStatus.OK);
    }

    @DeleteMapping("/payments/{instalmentPaymentId}")
    public void deleteInstalmentPayment(@PathVariable Long instalmentPaymentId) {
        instalmentPaymentsService.deleteInstalmentPayment(instalmentPaymentId);
    }

    @PutMapping("/payments")
    public ResponseEntity<InstalmentPayments> updateInstalmentPayment(@RequestBody InstalmentPaymentsDTO instalmentPaymentsDTO) {
        return new ResponseEntity<>(instalmentPaymentsService.updateInstalmentPayment(instalmentPaymentsDTO), HttpStatus.OK);
    }
}
