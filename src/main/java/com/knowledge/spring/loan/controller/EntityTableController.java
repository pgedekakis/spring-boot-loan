package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.EntityTable;
import com.knowledge.spring.loan.service.EntityTableService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class EntityTableController {

    private EntityTableService entityTableService;

    public EntityTableController(EntityTableService entityTableService) {
        this.entityTableService = entityTableService;
    }
    @GetMapping("/entity")
    public List<EntityTable> getAllEntity(){
        return entityTableService.getAllEntity();
    }
}
