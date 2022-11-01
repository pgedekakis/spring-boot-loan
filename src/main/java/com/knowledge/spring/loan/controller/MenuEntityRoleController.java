package com.knowledge.spring.loan.controller;

import com.knowledge.spring.loan.model.MenuEntityRole;
import com.knowledge.spring.loan.service.MenuEntityRoleService;
import com.knowledge.spring.loan.service.dto.MenuEntityRoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/menuEntityRole")
    public ResponseEntity<MenuEntityRole> saveMenuEntityRole(@RequestBody MenuEntityRole menuEntityRole){
        return new ResponseEntity<>(menuEntityRoleService.saveMenuEntityRole(menuEntityRole),HttpStatus.CREATED);
    }
    @GetMapping("/menuEntityRole/{menuEntityRoleId}")
    public ResponseEntity<MenuEntityRole> getMenuEntityRoleById(@PathVariable Long menuEntityRoleId){
        return new ResponseEntity<>(menuEntityRoleService.getMenuEntityRoleById(menuEntityRoleId), HttpStatus.OK);
    }
    @DeleteMapping("/menuEntityRole/{menuEntityRoleId}")
    public void deleteMenuEntityRole(@PathVariable Long menuEntityRoleId){
        menuEntityRoleService.deleteMenuEntityRole(menuEntityRoleId);
    }
    @PutMapping("/menuEntityRole")
    public ResponseEntity<MenuEntityRole> updateMenuEntityRole(@RequestBody MenuEntityRoleDTO menuEntityRoleDTO){
        return new ResponseEntity<>(menuEntityRoleService.updateMenuEntityRole(menuEntityRoleDTO),HttpStatus.OK);
    }

}
