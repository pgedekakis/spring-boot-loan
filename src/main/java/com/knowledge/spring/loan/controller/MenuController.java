package com.knowledge.spring.loan.controller;


import com.knowledge.spring.loan.model.Menu;
import com.knowledge.spring.loan.repository.MenuRepository;
import com.knowledge.spring.loan.service.MenuService;
import com.knowledge.spring.loan.service.dto.MenuDTO;
import com.knowledge.spring.loan.service.dto.MenuRoleExtendedDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class MenuController {

    private MenuService menuService;
    private MenuRepository menuRepository;

    public MenuController(MenuService menuService,MenuRepository menuRepository) {
        this.menuService = menuService;
        this.menuRepository=menuRepository;
    }

    @GetMapping("/menu")
    public List<Menu> getAllMenu() {
        return menuService.getAllMenu();
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long menuId) {
        return new ResponseEntity<>(menuService.getMenuById(menuId), HttpStatus.OK);
    }

    @DeleteMapping("menu/{menuId}")
    public void deleteMenu(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
    }

    @PutMapping("/menu")
    public ResponseEntity<Menu> updateMenu(@RequestBody MenuDTO menuDTO) {
        return new ResponseEntity<>(menuService.updateMenu(menuDTO), HttpStatus.OK);
    }

    @PostMapping("/menu/form")
    public MenuRoleExtendedDTO createMenuRole(@RequestBody MenuRoleExtendedDTO menuRoleExtendedDTO, HttpServletRequest request, HttpServletResponse response) {
        Optional<Menu> menuExists=menuRepository.findById(menuRoleExtendedDTO.getId());
        if(menuExists.isPresent()){
            log.info("Menu already exists,try updating");
        }
        else if(menuRoleExtendedDTO != null ) {
            menuService.createMenuRoles(menuRoleExtendedDTO);
        }
        else if(menuRoleExtendedDTO == null){
            log.info("DTO is null");
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST  );
        }
         return menuRoleExtendedDTO;
    }
}
