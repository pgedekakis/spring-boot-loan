package com.knowledge.spring.loan.controller;

import com.knowledge.spring.loan.model.RoleMenu;
import com.knowledge.spring.loan.service.RoleMenuService;
import com.knowledge.spring.loan.service.dto.RoleMenuDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/roleMenu")
    public ResponseEntity<RoleMenu> saveRoleMenu(@RequestBody RoleMenu roleMenu){
        return new ResponseEntity<>(roleMenuService.saveRoleMenu(roleMenu),HttpStatus.CREATED);
    }
    @GetMapping("/roleMenu/{roleMenuId}")
    public ResponseEntity<RoleMenu> getRoleMenuById(@PathVariable Long roleMenuId){
        return new ResponseEntity<>(roleMenuService.getRoleMenuById(roleMenuId), HttpStatus.OK);

    }
    @DeleteMapping("roleMenu/{roleMenuId}")
    public void deleteRoleMenu(@PathVariable Long roleMenuId){
        roleMenuService.deleteRoleMenu(roleMenuId);
    }
    @PutMapping("/roleMenu")
    public ResponseEntity<RoleMenu> updateRoleMenu(@RequestBody RoleMenuDTO roleMenuDTO){
        return new ResponseEntity<>(roleMenuService.updateRoleMenu(roleMenuDTO),HttpStatus.OK);
    }
}
