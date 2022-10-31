package com.knowledge.spring.loan.service;

import com.knowledge.spring.loan.model.RoleMenu;
import com.knowledge.spring.loan.repository.RoleMenuRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class RoleMenuService {

    private RoleMenuRepository roleMenuRepository;

    public RoleMenuService(RoleMenuRepository roleMenuRepository) {
        this.roleMenuRepository = roleMenuRepository;
    }
    public List<RoleMenu> getAllRoleMenu(){
        return roleMenuRepository.findAll();
    }
}
