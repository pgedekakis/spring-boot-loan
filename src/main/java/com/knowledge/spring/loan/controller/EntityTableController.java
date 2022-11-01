package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.EntityTable;
import com.knowledge.spring.loan.service.EntityTableService;
import com.knowledge.spring.loan.service.dto.EntityTableDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class EntityTableController {

    private EntityTableService entityTableService;

    public EntityTableController(EntityTableService entityTableService) {
        this.entityTableService = entityTableService;
    }
    @PostMapping("/entity")
    public ResponseEntity<EntityTable> saveEntity(@RequestBody EntityTable entityTable){
        return new ResponseEntity<>(entityTableService.saveEntity(entityTable),HttpStatus.CREATED);
    }
    @GetMapping("/entity")
    public List<EntityTable> getAllEntity(){
        return entityTableService.getAllEntity();
    }
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<EntityTable> getEntityById(@PathVariable Long entityId){
        return new ResponseEntity<>(entityTableService.getEntityById(entityId), HttpStatus.OK);
    }
    @DeleteMapping("entity/{entityId}")
    public void deleteEntity(@PathVariable Long entityId){
        entityTableService.deleteEntity(entityId);
    }
    @PutMapping("entity")
    public ResponseEntity<EntityTable> updateEntity(@RequestBody EntityTableDTO entityTableDTO){
        return new ResponseEntity<>(entityTableService.updateEntity(entityTableDTO),HttpStatus.OK);
    }
}
