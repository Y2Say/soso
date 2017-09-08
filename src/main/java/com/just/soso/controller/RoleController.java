package com.just.soso.controller;
import com.just.soso.entity.Role;
import com.just.soso.entity.RoleFunction;
import com.just.soso.repository.RoleRepository;
import com.just.soso.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;
    /**
     * 分页查询角色
     *
     * @param page
     * @param rows
     * @return 返回查询结果
     */
    @RequestMapping(value = "/findAllRole", method = RequestMethod.GET)
    public String findRoles(String orderType, Integer page, Integer rows, Model model) {
        Page<Role> rolePage = roleService.getRoles("desc", page, rows);
        if(!rolePage.getContent().isEmpty()){
            model.addAttribute("roles",rolePage.getContent());
        }
        return "role/role_list";
    }

    /**
     * 查询所有角色列表
     * @return
     */
    @RequestMapping(value = "/role_list", method = RequestMethod.GET)
    public String findAll(Model model){
        List<Role> roles = roleRepository.findAll();
        if(!roles.isEmpty()){
            model.addAttribute("roles",roles);
        }
        return "role/role_list";
    }
    /**
     * 新增、修改角色
     * @param role
     * @return 返回成功
     */
    @RequestMapping(value = "/addEditRole", method = RequestMethod.POST)
    public String addEditRole(Role role) {

        String functionIds = role.getFunctionIds();
        String[] idArray = functionIds.split(",");
        List<RoleFunction> roleFunctions = new ArrayList<>();
        for (int i = 0; i < idArray.length; i++) {
            RoleFunction rf = new RoleFunction();
            rf.setFunctionId(Integer.valueOf(idArray[i]));
            rf.setStatus(1);
            roleFunctions.add(rf);
        }

        if (Objects.equals(null,role.getId())) {
            roleService.addRole(role, roleFunctions);
        } else {
            roleService.editRole(role, roleFunctions);
        }

        return "role/role_list";
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return 返回成功
     */
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public String deleteRole(Integer roleId) {
        roleService.deleteRole(roleId);
        return "role/role_list";
    }
}
