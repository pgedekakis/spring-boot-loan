package com.knowledge.spring.loan.service;

import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.*;
import com.knowledge.spring.loan.repository.MenuEntityRepository;
import com.knowledge.spring.loan.repository.MenuEntityRoleRepository;
import com.knowledge.spring.loan.repository.MenuRepository;
import com.knowledge.spring.loan.repository.RoleMenuRepository;
import com.knowledge.spring.loan.service.dto.MenuDTO;
import com.knowledge.spring.loan.service.dto.MenuRoleExtendedDTO;
import com.knowledge.spring.loan.service.mapper.MenuMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class MenuService {
    private MenuRepository menuRepository;
    private MenuMapper menuMapper;

    private RoleMenuRepository roleMenuRepository;

    private MenuEntityRoleRepository menuEntityRoleRepository;

    private MenuEntityRepository menuEntityRepository;

    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper, RoleMenuRepository roleMenuRepository,
                       MenuEntityRoleRepository menuEntityRoleRepository, MenuEntityRepository menuEntityRepository) {

        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.roleMenuRepository = roleMenuRepository;
        this.menuEntityRoleRepository = menuEntityRoleRepository;
        this.menuEntityRepository = menuEntityRepository;
    }

    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    public Menu getMenuById(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu doesn't exist with this id :" + menuId));
        return menu;
    }

    public void deleteMenu(Long menuId) {
        Optional<Menu> menuExists = menuRepository.findById(menuId);
        List<Long> roleMenuIds = roleMenuRepository.getRoleMenuIds(menuId);
        List<Long> menuEntityIds = menuEntityRepository.getRoleMenuIds(menuId);
        List<Long> menuEntityRoleIds = menuEntityRoleRepository.getRoleMenuEntityIds(menuId);
        if (menuExists.isPresent()) {
            if (!menuEntityRoleIds.isEmpty()) {
                menuEntityRoleRepository.deleteAllById(menuEntityRoleIds);
            }
            if (!roleMenuIds.isEmpty()) {
                roleMenuRepository.deleteAllById(roleMenuIds);
            }
            if (!menuEntityIds.isEmpty()) {
                menuEntityRepository.deleteAllById(menuEntityIds);
            }
        }
    }

    public Menu updateMenu(MenuDTO menuDTO) {
        Optional<Menu> menuExists = menuRepository.findById(menuDTO.getId());
        Menu menu = menuMapper.toEntity(menuDTO);
        if (menuExists.isPresent()) {
            menuRepository.save(menu);
        } else {
            log.info("Cannot update non existent menu");
        }
        return menu;
    }

    public MenuRoleExtendedDTO createMenuRoles(MenuRoleExtendedDTO menuRoleExtendedDTO) {
        Menu menu = menuMapper.toEntity(menuRoleExtendedDTO.getMenuDTO());
        Menu newMenu = menuRepository.save(menu);
        createRoleEntityLoop(menuRoleExtendedDTO, newMenu);
        return menuRoleExtendedDTO;
    }

    public MenuRoleExtendedDTO createRoleEntityLoop(MenuRoleExtendedDTO menuRoleExtendedDTO, Menu menu) {
        Set<Role> roleList = menuRoleExtendedDTO.getRoleList();
        Set<EntityTable> entityList = menuRoleExtendedDTO.getEntityList();
        List<RoleMenu> roleMenuList = new ArrayList<>();
        List<MenuEntity> menuEntityList = new ArrayList<>();
        for (Role role : roleList) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRole(role);
            roleMenuList.add(roleMenu);
        }
        for (EntityTable entityTable : entityList) {
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.setMenu(menu);
            menuEntity.setEntityTable(entityTable);
            menuEntityList.add(menuEntity);
        }
        roleMenuRepository.saveAll(roleMenuList);
        menuEntityRepository.saveAll(menuEntityList);
        return menuRoleExtendedDTO;
    }
}
