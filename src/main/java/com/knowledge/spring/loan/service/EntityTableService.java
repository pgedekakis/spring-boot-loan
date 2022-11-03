package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.EntityTable;
import com.knowledge.spring.loan.repository.EntityTableRepository;
import com.knowledge.spring.loan.repository.MenuEntityRepository;
import com.knowledge.spring.loan.repository.MenuEntityRoleRepository;
import com.knowledge.spring.loan.service.dto.EntityTableDTO;
import com.knowledge.spring.loan.service.mapper.EntityTableMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class EntityTableService {

    private EntityTableRepository entityTableRepository;
    private MenuEntityRepository menuEntityRepository;
    private MenuEntityRoleRepository menuEntityRoleRepository;
    private EntityTableMapper entityTableMapper;

    public EntityTableService(EntityTableRepository entityTableRepository, MenuEntityRepository menuEntityRepository,
                              MenuEntityRoleRepository menuEntityRoleRepository, EntityTableMapper entityTableMapper) {
        this.entityTableRepository = entityTableRepository;
        this.menuEntityRepository = menuEntityRepository;
        this.menuEntityRoleRepository = menuEntityRoleRepository;
        this.entityTableMapper = entityTableMapper;
    }

    public List<EntityTable> getAllEntity() {
        return entityTableRepository.findAll();
    }

    public EntityTable saveEntity(EntityTable entityTable) {
        return entityTableRepository.save(entityTable);
    }

    public EntityTable getEntityById(Long entityId) {
        EntityTable entityTable = entityTableRepository.findById(entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Entity does not exist with id:" + entityId));
        return entityTable;
    }

    public void deleteEntity(Long entityId) {
        Optional<EntityTable> entityExists = entityTableRepository.findById(entityId);
        List<Long> menuEntityIds = menuEntityRepository.getMenuRoleIds(entityId);
        List<Long> menuEntityRoleIds = menuEntityRoleRepository.getEntityIds(entityId);
        if (entityExists.isPresent()) {
            if (!menuEntityRoleIds.isEmpty()) {
                menuEntityRoleRepository.deleteAllById(menuEntityRoleIds);
            }
            if (!menuEntityIds.isEmpty()) {
                menuEntityRepository.deleteAllById(menuEntityIds);
            }
            entityTableRepository.deleteById(entityId);
        } else {
            log.info("Entity does not exists with this id");
        }
    }

    public EntityTable updateEntity(EntityTableDTO entityTableDTO) {
        Optional<EntityTable> entityExists = entityTableRepository.findById(entityTableDTO.getId());
        EntityTable entityTable = entityTableMapper.toEntity(entityTableDTO);
        if (entityExists.isPresent()) {
            entityTableRepository.save(entityTable);
        } else {
            log.info("Cannot update non existent entity");
        }
        return entityTable;
    }
}
