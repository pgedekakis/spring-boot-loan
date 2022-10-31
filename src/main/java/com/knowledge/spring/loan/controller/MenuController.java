package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.Menu;
import com.knowledge.spring.loan.service.MenuService;
import com.knowledge.spring.loan.service.dto.MenuRoleExtendedDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class MenuController {

private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public List<Menu> getAllMenu(){
        return menuService.getAllMenu();
    }

    @PostMapping("/menu/form")
    public MenuRoleExtendedDTO createMenuRole(@RequestBody MenuRoleExtendedDTO menuRoleExtendedDTO){
        return menuService.createMenu(menuRoleExtendedDTO);
    }
}
