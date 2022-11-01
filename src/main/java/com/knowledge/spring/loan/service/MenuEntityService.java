package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.MenuEntity;
import com.knowledge.spring.loan.repository.MenuEntityRepository;
import com.knowledge.spring.loan.repository.MenuEntityRoleRepository;
import com.knowledge.spring.loan.service.dto.MenuEntityDTO;
import com.knowledge.spring.loan.service.mapper.MenuEntityMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class MenuEntityService {

    private MenuEntityRepository menuEntityRepository;
    private MenuEntityRoleRepository menuEntityRoleRepository;
    private MenuEntityMapper menuEntityMapper;

    public MenuEntityService(MenuEntityRepository menuEntityRepository,MenuEntityRoleRepository menuEntityRoleRepository,MenuEntityMapper menuEntityMapper) {
        this.menuEntityRepository = menuEntityRepository;
        this.menuEntityRoleRepository=menuEntityRoleRepository;
        this.menuEntityMapper=menuEntityMapper;
    }

    public List<MenuEntity> getAllMenuEntity(){
        return menuEntityRepository.findAll();
    }

    public MenuEntity saveMenuEntity(MenuEntity menuEntity){
        return menuEntityRepository.save(menuEntity);
    }
    public MenuEntity getMenuEntityById(Long menuEntityId){
        MenuEntity menuEntity=menuEntityRepository.findById(menuEntityId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu Entity not found with id:"+menuEntityId));
                return menuEntity;
    }
    public void deleteMenuEntity(Long menuEntityId){
        Optional<MenuEntity> menuEntityExists=menuEntityRepository.findById(menuEntityId);
        List<Long> menuEntityIds=menuEntityRoleRepository.getMenuEntityIds(menuEntityId);
        if(menuEntityExists.isPresent()){
            if(!menuEntityIds.isEmpty()){
                menuEntityRoleRepository.deleteAllById(menuEntityIds);
            }
            menuEntityRepository.deleteById(menuEntityId);
        }else{
            log.info("Menu Entity does not exists with this id");
        }
    }
    public MenuEntity updateMenuEntity(MenuEntityDTO menuEntityDTO){
        Optional<MenuEntity> menuEntityExists= menuEntityRepository.findById(menuEntityDTO.getId());
        MenuEntity menuEntity=menuEntityMapper.toEntity(menuEntityDTO);
        if(menuEntityExists.isPresent()){
            menuEntityRepository.save(menuEntity);
        }else{
            log.info("Cannot update non existent menu entity");
        }
        return menuEntity;
    }
}
