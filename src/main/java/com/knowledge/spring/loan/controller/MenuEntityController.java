package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.MenuEntity;
import com.knowledge.spring.loan.service.MenuEntityService;
import com.knowledge.spring.loan.service.dto.MenuEntityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/menuEntity")
    public ResponseEntity<MenuEntity> saveMenuEntity(@RequestBody MenuEntity menuEntity){
        return new ResponseEntity<>(menuEntityService.saveMenuEntity(menuEntity),HttpStatus.CREATED);
    }
@GetMapping("menuEntity/{menuEntityId}")
    public ResponseEntity<MenuEntity> getMenuEntityById(@PathVariable Long menuEntityId){
        return new ResponseEntity<>(menuEntityService.getMenuEntityById(menuEntityId), HttpStatus.OK);
}
@DeleteMapping("menuEntity/{menuEntityId}")
    public void deleteMenuEntity(@PathVariable Long menuEntityId){
        menuEntityService.deleteMenuEntity(menuEntityId);
}
@PutMapping("/menuEntity")
    public  ResponseEntity<MenuEntity> updateMenuEntity(@RequestBody MenuEntityDTO menuEntityDTO){
        return new ResponseEntity<>(menuEntityService.updateMenuEntity(menuEntityDTO),HttpStatus.OK);
}
}
