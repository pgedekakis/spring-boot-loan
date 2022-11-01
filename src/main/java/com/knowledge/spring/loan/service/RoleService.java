package com.knowledge.spring.loan.service;


import com.knowledge.spring.loan.excption.ResourceNotFoundException;
import com.knowledge.spring.loan.model.MenuEntityRole;
import com.knowledge.spring.loan.model.Role;
import com.knowledge.spring.loan.repository.MenuEntityRoleRepository;
import com.knowledge.spring.loan.repository.RoleMenuRepository;
import com.knowledge.spring.loan.repository.RoleRepository;
import com.knowledge.spring.loan.service.dto.RoleDTO;
import com.knowledge.spring.loan.service.mapper.RoleMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class RoleService {
    private RoleRepository roleRepository;
    private RoleMenuRepository roleMenuRepository;
    private MenuEntityRoleRepository menuEntityRoleRepository;
    private RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMenuRepository roleMenuRepository,
                       MenuEntityRoleRepository menuEntityRoleRepository, RoleMapper roleMapper) {

        this.roleRepository = roleRepository;
        this.roleMenuRepository = roleMenuRepository;
        this.menuEntityRoleRepository = menuEntityRoleRepository;
        this.roleMapper = roleMapper;
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
            if (!roleMenuIds.isEmpty()) {
                roleMenuRepository.deleteAllById(roleMenuIds);
            }
            if (!menuRoleEntityIds.isEmpty()) {
                menuEntityRoleRepository.deleteAllById(menuRoleEntityIds);
            }
            roleRepository.deleteById(roleId);
        } else {
            log.info("Role does not exist");
        }
    }

    public Role updateRole(RoleDTO roleDTO) {
        Optional<Role> roleExists = roleRepository.findById(roleDTO.getId());
        log.info("DTO is :{}",roleDTO);
        Role role = roleMapper.toEntity(roleDTO);
        if (roleExists.isPresent()) {
            roleRepository.save(role);
        } else {
            log.info("Cannot update non existent role");
        }
        return role;
    }

}
