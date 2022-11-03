package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.controller.EntityExtendDTO;
import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.*;
import com.knowledge.spring.loan.repository.*;
import com.knowledge.spring.loan.service.dto.MenuExtendDTO;
import com.knowledge.spring.loan.service.dto.PropertyDTO;
import com.knowledge.spring.loan.service.dto.RoleDTO;
import com.knowledge.spring.loan.service.dto.RoleExtendDTO;
import com.knowledge.spring.loan.service.mapper.EntityTableMapper;
import com.knowledge.spring.loan.service.mapper.MenuMapper;
import com.knowledge.spring.loan.service.mapper.RoleMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class RoleService {
    private RoleRepository roleRepository;
    private RoleMenuRepository roleMenuRepository;
    private MenuEntityRoleRepository menuEntityRoleRepository;
    private RoleMapper roleMapper;
    private MenuMapper menuMapper;
    private MenuEntityRepository menuEntityRepository;
    private EntityTableMapper entityTableMapper;
    private MenuRepository menuRepository;

    public RoleService(RoleRepository roleRepository, RoleMenuRepository roleMenuRepository,
                       MenuEntityRoleRepository menuEntityRoleRepository, RoleMapper roleMapper, MenuMapper menuMapper,
                       MenuEntityRepository menuEntityRepository, EntityTableMapper entityTableMapper,
                       MenuRepository menuRepository) {

        this.roleRepository = roleRepository;
        this.roleMenuRepository = roleMenuRepository;
        this.menuEntityRoleRepository = menuEntityRoleRepository;
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
        this.menuEntityRepository = menuEntityRepository;
        this.entityTableMapper = entityTableMapper;
        this.menuRepository = menuRepository;
    }

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role does not exist with id:" + roleId));
        return role;
    }

    public void deleteRole(Long roleId) {
        Optional<Role> roleExists = roleRepository.findById(roleId);
        List<Long> roleMenuIds = roleMenuRepository.getMenuRoleIds(roleId);
        List<Long> menuRoleEntityIds = menuEntityRoleRepository.getMenuRoleEntityIds(roleId);
        if (roleExists.isPresent()) {
            if (!menuRoleEntityIds.isEmpty()) {
                menuEntityRoleRepository.deleteAllById(menuRoleEntityIds);
            }
            if (!roleMenuIds.isEmpty()) {
                roleMenuRepository.deleteAllById(roleMenuIds);
            }
            roleRepository.deleteById(roleId);
        } else {
            log.info("Role does not exist");
        }
    }

    public Role updateRole(RoleDTO roleDTO) {
        Optional<Role> roleExists = roleRepository.findById(roleDTO.getId());
        log.info("DTO is :{}", roleDTO);
        Role role = roleMapper.toEntity(roleDTO);
        if (roleExists.isPresent()) {
            roleRepository.save(role);
        } else {
            log.info("Cannot update non existent role");
        }
        return role;
    }

    public RoleExtendDTO createRole(RoleExtendDTO roleExtendDTO) {
        Role role = roleMapper.toEntity(roleExtendDTO.getRoleDTO());
        Role newRole = roleRepository.save(role);

        List<RoleMenu> roleMenuList = setRoleMenu(roleExtendDTO, newRole);

        List<MenuEntity> menuEntityList = setMenuEntity(roleExtendDTO);

        createMenuEntityRole(roleExtendDTO, menuEntityList, roleMenuList);

        return roleExtendDTO;
    }

    public List<RoleMenu> setRoleMenu(RoleExtendDTO roleExtendDTO, Role role) {
        List<MenuExtendDTO> menuList = roleExtendDTO.getMenuList();
        List<RoleMenu> roleMenuList = new ArrayList<>();
        for (MenuExtendDTO menuExtend : menuList) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRole(role);
            roleMenu.setMenu(menuMapper.toEntity(menuExtend.getMenuDTO()));
            roleMenuList.add(roleMenu);
        }
        roleMenuRepository.saveAll(roleMenuList);
        return roleMenuList;
    }

    public List<MenuEntity> setMenuEntity(RoleExtendDTO roleExtendDTO) {
        List<MenuExtendDTO> menuList = roleExtendDTO.getMenuList();
        List<MenuEntity> menuEntityList = new ArrayList<>();
        for (MenuExtendDTO menuExtDTO : menuList) {
            List<EntityExtendDTO> entityList = menuExtDTO.getEntityList();
            Menu menu = menuMapper.toEntity(menuExtDTO.getMenuDTO());
            for (EntityExtendDTO entityExtDTO : entityList) {
                MenuEntity menuEntity = new MenuEntity();
                menuEntity.setEntityTable(entityTableMapper.toEntity(entityExtDTO.getEntityDto()));
                menuEntity.setMenu(menu);
                menuEntityList.add(menuEntity);
            }
        }
        menuEntityRepository.saveAll(menuEntityList);
        return menuEntityList;
    }

    public List<MenuEntityRole> createMenuEntityRole(RoleExtendDTO roleExtendDTO, List<MenuEntity> menuEntityList, List<RoleMenu> roleMenuList) {
        List<MenuEntityRole> menuEntityRoleList = new ArrayList<>();
        HashMap<EntityExtendDTO, PropertyDTO> entityPropertyMap = new HashMap<EntityExtendDTO, PropertyDTO>();
        int menuIndex;
        int entityIndex = 0;
        int menuListSize;
        for (RoleMenu roleMenu : roleMenuList) {
            menuIndex = 0;
            for (MenuEntity menuEntity : menuEntityList) {
                List<MenuExtendDTO> menuList = roleExtendDTO.getMenuList();
                MenuExtendDTO menuFormListDTO = menuList.get(menuIndex);
                menuListSize = menuFormListDTO.getPropertyList().size();
                List<PropertyDTO> propertyList = menuFormListDTO.getPropertyList();
                PropertyDTO propertyDTO = propertyList.get(entityIndex);
                List<EntityExtendDTO> entityExtendDTOList = menuFormListDTO.getEntityList();
                EntityExtendDTO entityExtendDTO = entityExtendDTOList.get(entityIndex);
                entityPropertyMap.put(entityExtendDTO, propertyDTO);
                MenuEntityRole menuEntityRole = setMenuEntityRole(entityPropertyMap.get(entityExtendDTO), menuEntity, roleMenu);
                menuEntityRoleList.add(menuEntityRole);
                if (entityIndex == menuListSize - 1) {
                    menuIndex++;
                    entityIndex = 0;
                } else {
                    entityIndex++;
                }
            }
        }
        menuEntityRoleRepository.saveAll(menuEntityRoleList);
        return menuEntityRoleList;
    }

    public MenuEntityRole setMenuEntityRole(PropertyDTO propertyDTO, MenuEntity menuEntity, RoleMenu roleMenu) {
        MenuEntityRole menuEntityRole = new MenuEntityRole();
        menuEntityRole.setInsert(propertyDTO.getInsert());
        menuEntityRole.setUpdate(propertyDTO.getUpdate());
        menuEntityRole.setDelete(propertyDTO.getDelete());
        menuEntityRole.setMenuEntity(menuEntity);
        menuEntityRole.setRoleMenu(roleMenu);
        return menuEntityRole;
    }
/*
    public RoleExtendDTO getRoleInfo(Long roleId){
        Optional<Role> roleExists=roleRepository.findById(roleId);
        List<MenuExtendDTO> menuList=new ArrayList<>();
        if(roleExists.isPresent()){
            Role role=roleExists.get();
            RoleExtendDTO roleExtendDTO=new RoleExtendDTO();
            RoleDTO roleDTO=roleMapper.toDTO(role);
            roleExtendDTO.setRoleDTO(roleDTO);
            List<RoleMenu> roleMenuList=roleMenuRepository.getRoleMenu(roleId);
            for(RoleMenu roleMenu:roleMenuList){
                List<MenuEntity> menuEntityList=menuEntityRepository.getMenuEntity(roleMenu.getMenu().getId());
                menuList.add(menu);
            }
            roleExtendDTO.setMenuList(menuList);
        }else {
            log.info("Role does not exist");
        }

    }

 */
}
