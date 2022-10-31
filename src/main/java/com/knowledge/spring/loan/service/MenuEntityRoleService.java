package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.model.MenuEntityRole;
import com.knowledge.spring.loan.repository.MenuEntityRoleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class MenuEntityRoleService {

    private MenuEntityRoleRepository menuEntityRoleRepository;

    public MenuEntityRoleService(MenuEntityRoleRepository menuEntityRoleRepository) {
        this.menuEntityRoleRepository = menuEntityRoleRepository;
    }

    public List<MenuEntityRole> getAllMenuEntityRole(){
        return menuEntityRoleRepository.findAll();
    }
}
