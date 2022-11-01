package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.MenuEntityRole;
import com.knowledge.spring.loan.repository.MenuEntityRoleRepository;
import com.knowledge.spring.loan.service.dto.MenuEntityRoleDTO;
import com.knowledge.spring.loan.service.mapper.MenuEntityRoleMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class MenuEntityRoleService {

    private MenuEntityRoleRepository menuEntityRoleRepository;
    private MenuEntityRoleMapper menuEntityRoleMapper;

    public MenuEntityRoleService(MenuEntityRoleRepository menuEntityRoleRepository,MenuEntityRoleMapper menuEntityRoleMapper) {
        this.menuEntityRoleRepository = menuEntityRoleRepository;
        this.menuEntityRoleMapper=menuEntityRoleMapper;
    }

    public List<MenuEntityRole> getAllMenuEntityRole(){
        return menuEntityRoleRepository.findAll();
    }

    public MenuEntityRole saveMenuEntityRole(MenuEntityRole menuEntityRole){
       return menuEntityRoleRepository.save(menuEntityRole);
    }
    public MenuEntityRole getMenuEntityRoleById(Long menuEntityRoleId){
        MenuEntityRole menuEntityRole= menuEntityRoleRepository.findById(menuEntityRoleId)
                .orElseThrow(()-> new ResourceNotFoundException("MenuEntityRole not found with id:"+menuEntityRoleId));
                return menuEntityRole;
    }

    public void deleteMenuEntityRole(Long menuEntityRoleId){
        Optional<MenuEntityRole> menuEntityRoleExists=menuEntityRoleRepository.findById(menuEntityRoleId);
        if(menuEntityRoleExists.isPresent()){
            menuEntityRoleRepository.deleteById(menuEntityRoleId);
        }else{
            log.info("MenuEntityRole does not exist with this id");
        }
    }
    public MenuEntityRole updateMenuEntityRole(MenuEntityRoleDTO menuEntityRoleDTO){
        Optional<MenuEntityRole> menuEntityRoleExists=menuEntityRoleRepository.findById(menuEntityRoleDTO.getId());
        MenuEntityRole menuEntityRole=menuEntityRoleMapper.toEntity(menuEntityRoleDTO);
        if(menuEntityRoleExists.isPresent()){
            menuEntityRoleRepository.save(menuEntityRole);
        }else{
            log.info("Cannot update non existent MenuEntityRole");
        }
        return menuEntityRole;
    }
}
