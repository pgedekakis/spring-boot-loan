package com.knowledge.spring.loan.controller;

import com.knowledge.spring.loan.model.MenuEntityRole;
import com.knowledge.spring.loan.service.MenuEntityRoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class MenuEntityRoleController {

    private MenuEntityRoleService menuEntityRoleService;

    public MenuEntityRoleController(MenuEntityRoleService menuEntityRoleService) {
        this.menuEntityRoleService = menuEntityRoleService;
    }

    @GetMapping("/menuEntityRole")
    public List<MenuEntityRole> getAllMenuEntityRole(){
        return menuEntityRoleService.getAllMenuEntityRole();
    }
}
