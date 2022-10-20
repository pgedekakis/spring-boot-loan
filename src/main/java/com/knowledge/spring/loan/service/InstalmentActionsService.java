package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.InstalmentActions;
import com.knowledge.spring.loan.repository.InstalmentActionsRepository;
import com.knowledge.spring.loan.service.dto.InstalmentActionsDTO;
import com.knowledge.spring.loan.service.mapper.InstalmentActionMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class InstalmentActionsService {

    private InstalmentActionsRepository instalmentActionsRepository;
    private InstalmentActionMapper instalmentActionMapper;

    public InstalmentActionsService(InstalmentActionsRepository instalmentActionsRepository, InstalmentActionMapper instalmentActionMapper) {
        this.instalmentActionsRepository = instalmentActionsRepository;
        this.instalmentActionMapper = instalmentActionMapper;
    }

    public InstalmentActions saveInstalmentAction(InstalmentActions instalmentActions) {
        return instalmentActionsRepository.save(instalmentActions);
    }

    public List<InstalmentActions> getAllInstalmentActions() {
        return instalmentActionsRepository.findAll();
    }

    public InstalmentActions getInstalmentActionById(Long workingCapitalId) {
        InstalmentActions instalmentActions = instalmentActionsRepository.findById(workingCapitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Instalment Action doesn't exist with this id :" + workingCapitalId));
        return instalmentActions;
    }

    public void deleteInstalmentAction(Long workingCapitalId) {
        Optional<InstalmentActions> workingCapitalExists = instalmentActionsRepository.findById(workingCapitalId);
        if (workingCapitalExists.isPresent()) {
            instalmentActionsRepository.deleteById(workingCapitalId);
        } else {
            log.info("Instalment Action does not exist with this id");
        }
    }

    public InstalmentActions updateWorkingCaptial(InstalmentActionsDTO instalmentActionsDTO) {
        Optional<InstalmentActions> workingCaprialExists = instalmentActionsRepository.findById(instalmentActionsDTO.getId());
        InstalmentActions instalmentActions = instalmentActionMapper.toEntity(instalmentActionsDTO);
        if (workingCaprialExists.isPresent()) {
            instalmentActionsRepository.save(instalmentActions);
        } else {
            log.info("Cannot update non existent intalment action");
        }
        return instalmentActions;
    }
}
