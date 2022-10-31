package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.model.MenuEntity;
import com.knowledge.spring.loan.repository.MenuEntityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class MenuEntityService {

    private MenuEntityRepository menuEntityRepository;

    public MenuEntityService(MenuEntityRepository menuEntityRepository) {
        this.menuEntityRepository = menuEntityRepository;
    }

    public List<MenuEntity> getAllMenuEntity(){
        return menuEntityRepository.findAll();
    }
}
