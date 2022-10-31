package com.knowledge.spring.loan.controller;

import com.knowledge.spring.loan.model.RoleMenu;
import com.knowledge.spring.loan.service.RoleMenuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class RoleMenuController {

    private RoleMenuService roleMenuService;

    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    @GetMapping("/roleMenu")
    public List<RoleMenu> getAllRoleMenu(){
        return roleMenuService.getAllRoleMenu();
    }
}
