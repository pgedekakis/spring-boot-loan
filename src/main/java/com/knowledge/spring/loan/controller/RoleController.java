package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.Role;
import com.knowledge.spring.loan.service.RoleService;
import com.knowledge.spring.loan.service.dto.RoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/role")
    public List<Role> getAllRole() {
        return roleService.getAllRole();
    }
    @PostMapping("/role")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
    }
    @GetMapping("/role/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long roleId){
        return new ResponseEntity<>(roleService.getRoleById(roleId),HttpStatus.OK);
    }
    @DeleteMapping("/role/{roleId}")
    public void deleteRole(@PathVariable Long roleId){
        roleService.deleteRole(roleId);
    }
    @PutMapping("/role")
    public ResponseEntity<Role> updateRole(@RequestBody RoleDTO roleDTO){
        return new ResponseEntity<>(roleService.updateRole(roleDTO),HttpStatus.OK);
    }

}
