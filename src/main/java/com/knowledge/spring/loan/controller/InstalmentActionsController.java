package com.knowledge.spring.loan.controller;

import com.knowledge.spring.loan.model.InstalmentActions;
import com.knowledge.spring.loan.service.InstalmentActionsService;
import com.knowledge.spring.loan.service.dto.InstalmentActionsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class InstalmentActionsController {

    private InstalmentActionsService instalmentActionsService;

    public InstalmentActionsController(InstalmentActionsService instalmentActionsService) {
        this.instalmentActionsService = instalmentActionsService;
    }

    @PostMapping("/instalment-actions")
    public ResponseEntity<InstalmentActions> saveInstalmentAction(@RequestBody InstalmentActions instalmentActions) {
        return new ResponseEntity<InstalmentActions>(instalmentActionsService.saveInstalmentAction(instalmentActions), HttpStatus.CREATED);
    }

    @GetMapping("/instalment-actions")
    public List<InstalmentActions> getAllInstalmentActions() {
        return instalmentActionsService.getAllInstalmentActions();
    }

    @GetMapping("/instalment-actions/{instalmentActionsId}")
    public ResponseEntity<InstalmentActions> getInstalmentActionById(@PathVariable Long instalmentActionsId) {
        return new ResponseEntity<>(instalmentActionsService.getInstalmentActionById(instalmentActionsId), HttpStatus.OK);
    }

    @DeleteMapping("/instalment-action/{instalmentActionId}")
    public void deleteInstalmentAction(@PathVariable Long instalmentActionId) {
        instalmentActionsService.deleteInstalmentAction(instalmentActionId);
    }

    @PutMapping("/instalment-action")
    public ResponseEntity<InstalmentActions> updateInstalmentAction(@RequestBody InstalmentActionsDTO instalmentActionsDTO) {
        return new ResponseEntity<>(instalmentActionsService.updateInstalmentAction(instalmentActionsDTO), HttpStatus.OK);
    }
}
