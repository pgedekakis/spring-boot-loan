package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.PaymentActions;
import com.knowledge.spring.loan.service.PaymentActionsService;
import com.knowledge.spring.loan.service.dto.PaymentActionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class PaymentActionsController {
    private PaymentActionsService paymentActionsService;

    public PaymentActionsController(PaymentActionsService paymentActionsService) {
        this.paymentActionsService = paymentActionsService;
    }

    @PostMapping("/paymentAction")
    public ResponseEntity<PaymentActions> savePaymentAction(@RequestBody PaymentActions paymentActions) {
        return new ResponseEntity<>(paymentActionsService.savePaymentAction(paymentActions), HttpStatus.CREATED);
    }

    @GetMapping("/paymentAction")
    public List<PaymentActions> getAllPaymentActions() {
        return paymentActionsService.getAllPaymentActions();
    }

    @GetMapping("/paymentAction/{paymentActionsId}")
    public ResponseEntity<PaymentActions> getPaymentActionById(@PathVariable Long paymentActionsId) {
        return new ResponseEntity<>(paymentActionsService.getPaymentActionsById(paymentActionsId), HttpStatus.OK);
    }

    @DeleteMapping("/paymentAction/{paymentActionsId}")
    public void deletePaymentAction(@PathVariable Long paymentActionsId) {
        paymentActionsService.deletePaymentAction(paymentActionsId);
    }

    @PutMapping("/paymentActions")
    public ResponseEntity<PaymentActions> updatePaymentAction(@RequestBody PaymentActionDTO paymentActionDTO) {
        return new ResponseEntity<>(paymentActionsService.updatePaymentAction(paymentActionDTO), HttpStatus.OK);
    }

}
