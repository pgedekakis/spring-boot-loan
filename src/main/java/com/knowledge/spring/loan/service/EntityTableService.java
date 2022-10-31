package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.model.EntityTable;
import com.knowledge.spring.loan.repository.EntityTableRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class EntityTableService {

    private EntityTableRepository entityTableRepository;

    public EntityTableService(EntityTableRepository entityTableRepository) {
        this.entityTableRepository = entityTableRepository;
    }

    public List<EntityTable> getAllEntity(){
        return entityTableRepository.findAll();
    }
}
