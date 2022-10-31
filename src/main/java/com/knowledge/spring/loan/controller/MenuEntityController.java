package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.MenuEntity;
import com.knowledge.spring.loan.service.MenuEntityService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class MenuEntityController {

    private MenuEntityService menuEntityService;

    public MenuEntityController(MenuEntityService menuEntityService) {
        this.menuEntityService = menuEntityService;
    }
    @GetMapping("/menuEntity")
    public List<MenuEntity> getAllMenuEntity(){
        return  menuEntityService.getAllMenuEntity();
    }
}
