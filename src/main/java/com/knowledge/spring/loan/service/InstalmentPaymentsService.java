package com.knowledge.spring.loan.service;

import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.InstalmentPayments;
import com.knowledge.spring.loan.repository.InstalmentPaymentsRepository;
import com.knowledge.spring.loan.service.dto.InstalmentPaymentsDTO;
import com.knowledge.spring.loan.service.mapper.InstalmentPaymentsMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class InstalmentPaymentsService {

    private InstalmentPaymentsRepository instalmentPaymentsRepository;
    private InstalmentPaymentsMapper instalmentPaymentsMapper;

    public InstalmentPaymentsService(InstalmentPaymentsRepository instalmentPaymentsRepository, InstalmentPaymentsMapper instalmentPaymentsMapper) {
        this.instalmentPaymentsRepository = instalmentPaymentsRepository;
        this.instalmentPaymentsMapper = instalmentPaymentsMapper;
    }

    public InstalmentPayments saveInstalmentPayments(InstalmentPayments instalmentPayments) {
        return instalmentPaymentsRepository.save(instalmentPayments);
    }

    public List<InstalmentPayments> getAllInstalmentPayments() {
        return instalmentPaymentsRepository.findAll();
    }


    public InstalmentPayments getInstalmentPaymentById(Long paymentId) {
        InstalmentPayments instalmentPayments = instalmentPaymentsRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Instalment payment doesn't exist with this id :" + paymentId));
        return instalmentPayments;
    }

    public void deleteInstalmentPayment(Long paymentId) {
        Optional<InstalmentPayments> paymentExist = instalmentPaymentsRepository.findById(paymentId);
        if (paymentExist.isPresent()) {
            instalmentPaymentsRepository.deleteById(paymentId);
        } else {
            log.info("Bank does not exist with this id");
        }
    }


    public InstalmentPayments updateInstalmentPayment(InstalmentPaymentsDTO instalmentPaymentsDTO) {
        Optional<InstalmentPayments> paymentExists = instalmentPaymentsRepository.findById(instalmentPaymentsDTO.getId());
        InstalmentPayments instalmentPayments = instalmentPaymentsMapper.toEntity(instalmentPaymentsDTO);
        if (paymentExists.isPresent()) {
            instalmentPaymentsRepository.save(instalmentPayments);
        } else {
            log.info("Cannot update non existent instalment payment");
        }
        return instalmentPayments;
    }

}
