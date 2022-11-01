package com.knowledge.spring.loan.service;

import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.Role;
import com.knowledge.spring.loan.model.RoleMenu;
import com.knowledge.spring.loan.repository.MenuEntityRoleRepository;
import com.knowledge.spring.loan.repository.RoleMenuRepository;
import com.knowledge.spring.loan.service.dto.RoleMenuDTO;
import com.knowledge.spring.loan.service.mapper.RoleMenuMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class RoleMenuService {

    private RoleMenuRepository roleMenuRepository;
    private MenuEntityRoleRepository menuEntityRoleRepository;
    private RoleMenuMapper roleMenuMapper;

    public RoleMenuService(RoleMenuRepository roleMenuRepository, MenuEntityRoleRepository menuEntityRoleRepository,
                           RoleMenuMapper roleMenuMapper) {
        this.roleMenuRepository = roleMenuRepository;
        this.menuEntityRoleRepository = menuEntityRoleRepository;
        this.roleMenuMapper = roleMenuMapper;
    }

    public List<RoleMenu> getAllRoleMenu() {
        return roleMenuRepository.findAll();
    }

    public RoleMenu saveRoleMenu(RoleMenu roleMenu) {
        return roleMenuRepository.save(roleMenu);
    }

    public RoleMenu getRoleMenuById(Long roleMenuId) {
        RoleMenu roleMenu = roleMenuRepository.findById(roleMenuId)
                .orElseThrow(() -> new ResourceNotFoundException("RoleMenu not found with id:" + roleMenuId));
        return roleMenu;

    }

    public void deleteRoleMenu(Long roleMenuId) {
        Optional<RoleMenu> roleMenuExists = roleMenuRepository.findById(roleMenuId);
        List<Long> menuEntityRoleId = menuEntityRoleRepository.getMenuRoleEntity(roleMenuId);
        if (roleMenuExists.isPresent()) {
            if (!menuEntityRoleId.isEmpty()) {
                menuEntityRoleRepository.deleteAllById(menuEntityRoleId);
            }
            roleMenuRepository.deleteById(roleMenuId);
        } else {
            log.info("Role menu does not exists with this id");
        }
    }

    public RoleMenu updateRoleMenu(RoleMenuDTO roleMenuDTO) {
        Optional<RoleMenu> roleMenuExists = roleMenuRepository.findById(roleMenuDTO.getId());
        RoleMenu roleMenu = roleMenuMapper.toEntity(roleMenuDTO);
        if (roleMenuExists.isPresent()) {
            roleMenuRepository.save(roleMenu);
        } else {
            log.info("Cannot update non existent RoleMenu");
        }
        return roleMenu;
    }
}
