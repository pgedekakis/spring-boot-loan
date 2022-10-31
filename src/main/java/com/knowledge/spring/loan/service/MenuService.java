package com.knowledge.spring.loan.service;

import com.knowledge.spring.loan.model.Menu;
import com.knowledge.spring.loan.model.Role;
import com.knowledge.spring.loan.model.RoleMenu;
import com.knowledge.spring.loan.repository.MenuRepository;
import com.knowledge.spring.loan.repository.RoleMenuRepository;
import com.knowledge.spring.loan.service.dto.MenuRoleExtendedDTO;
import com.knowledge.spring.loan.service.mapper.MenuMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class MenuService {
    private MenuRepository menuRepository;
    private MenuMapper menuMapper;

    private RoleMenuRepository roleMenuRepository;

    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper, RoleMenuRepository roleMenuRepository) {

        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.roleMenuRepository = roleMenuRepository;
    }

    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    public MenuRoleExtendedDTO createMenu(MenuRoleExtendedDTO menuRoleExtendedDTO) {
        log.info("MenuDTO:{}",menuRoleExtendedDTO.getMenuDTO());
        log.info("Role is:{}",menuRoleExtendedDTO.getRole());
       // Menu menu = menuMapper.toEntity(menuRoleExtendedDTO.getMenuDTO());
        Menu menu=new Menu();
        menu.setDescription(menuRoleExtendedDTO.getDescription());
        menu.setPath(menuRoleExtendedDTO.getPath());
        menu.setDeactivationDate(menuRoleExtendedDTO.getDeactivationDate());
        Menu newMenu = menuRepository.save(menu);
        log.info("Old menu:{}",menu);
        log.info("Menu is :{}",newMenu);
        createMenuRole(menuRoleExtendedDTO,newMenu);
        return menuRoleExtendedDTO;
    }

    public MenuRoleExtendedDTO createMenuRole(MenuRoleExtendedDTO menuRoleExtendedDTO, Menu menu) {
        Role role = menuRoleExtendedDTO.getRole();
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setMenu(menu);
        roleMenu.setRole(role);
        roleMenuRepository.save(roleMenu);
        return menuRoleExtendedDTO;
    }
}
