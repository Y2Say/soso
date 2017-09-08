package com.just.soso.service;

import com.just.soso.entity.Role;
import com.just.soso.entity.RoleFunction;
import com.just.soso.repository.RoleFunctionRepository;
import com.just.soso.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by user on 2017/3/21.
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleFunctionRepository roleFunctionRepository;

    /**
     * 保存角色信息，同时保存角色对应的功能
     *
     * @param role
     * @param roleFunctions
     */
    public void addRole(Role role, Collection<RoleFunction> roleFunctions) {
        roleRepository.save(role);
        roleFunctions.forEach(rf -> rf.setRoleId(role.getId()));
        roleFunctionRepository.save(roleFunctions);
    }

    /**
     * 修改角色，同时修改角色功能
     *
     * @param role
     * @param roleFunctions
     */
    public void editRole(Role role, Collection<RoleFunction> roleFunctions) {
        roleRepository.save(role);
        roleFunctionRepository.deleteByRoleId(role.getId());
        roleFunctions.forEach(rf -> rf.setRoleId(role.getId()));
        roleFunctionRepository.save(roleFunctions);
    }

    /**
     * 根据角色Id删除角色及角色对应功能
     *
     * @param roleId
     */
    public void deleteRole(Integer roleId) {
        roleRepository.deleteRoleById(roleId);
        roleFunctionRepository.deleteByRoleId(roleId);
    }

    /**
     * 分页查询角色信息
     *
     * @param page
     * @param size
     * @return
     */
    public Page<Role> getRoles(String orderType, int page, int size) {
        Sort sort = new Sort(Sort.Direction.ASC);
        if ("desc".equalsIgnoreCase(orderType)) {
            sort = new Sort(Sort.Direction.DESC);
        }
        Pageable pageable = new PageRequest(page < 0 ? 0 : page, size < 0 ? 20 : size, sort);
        Page<Role> roles = roleRepository.findAll(pageable);
        roles.forEach(role -> {
            List<RoleFunction> roleFunctions = roleFunctionRepository.findRoleFunctionsByRoleId(role.getId());
            StringBuilder functionIds = new StringBuilder();
            roleFunctions.forEach(rf -> functionIds.append(rf.getFunctionId()).append(","));
            if (functionIds.length() > 1) {
                role.setFunctionIds(functionIds.deleteCharAt(functionIds.length() - 1).toString());
            }
        });
        return roles;
    }

    /**
     * 根据id集合查询角色集合
     *
     * @param ids
     * @return
     */
    public Collection<Role> findRolesById(Collection<Integer> ids) {
        Collection<Role> roleCollection = new ArrayList<>();
        ids.forEach(id -> {
            Role role = roleRepository.findById(id);
            roleCollection.add(role);
        });
        return roleCollection;
    }

    /**
     * 根据角色Id查询角色功能对应关系
     *
     * @param roleId
     * @return
     */
    public List<RoleFunction> findRoleFunctions(Integer roleId) {
        return roleFunctionRepository.findRoleFunctionsByRoleId(roleId);
    }
}
