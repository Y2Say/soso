package com.just.soso.controller;

import com.just.soso.common.AjaxResult;
import com.just.soso.entity.Authorize;
import com.just.soso.entity.Role;
import com.just.soso.entity.User;
import com.just.soso.entity.UserRole;
import com.just.soso.repository.UserRoleRepository;
import com.just.soso.service.RoleService;
import com.just.soso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

/**
 * Created by So on 2017/3/22.
 * ClassName UserAuthorizeController
 */
@Controller
public class UserAuthorizeController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @RequestMapping(value = "/user_role_list", method = RequestMethod.GET)
    public String authorizeIndex() {

        return "authorize/user_role_list";

    }

    /**
     * 查询权限信息
     *
     * @param page
     * @param rows
     * @return 权限集合
     */
    /*@RequestMapping(value = "/getAuthorizes", method = RequestMethod.POST)
    public String getAuthorizes(Integer page, Integer rows,Model model) {
        List<UserRole> userRoles = userService.findUserRoles("desc",page, rows).getContent();
        Collection<Integer> userIds = new HashSet<>();
        Collection<Integer> roleIds = new HashSet<>();
        userRoles.forEach(ur -> {
            userIds.add(ur.getUserId());
            roleIds.add(ur.getRoleId());
        });
        Collection<User> users = userService.findUsersById(userIds);
        Collection<Role> roles = roleService.findRolesById(roleIds);

        Map<Integer, User> userMap = User.idEntityMap(users);
        Map<Integer, Role> roleMap = Role.idEntityMap(roles);

        List<Authorize> authorizes = new LinkedList<>();
        userRoles.forEach(ur -> {
            Authorize authorize = new Authorize();
            authorize.setUserRoleId(ur.getId());
            authorize.setUserId(ur.getUserId());
            authorize.setUserName(userMap.get(ur.getUserId()).getName());
            authorize.setRoleId(ur.getRoleId());
            authorize.setRoleName(roleMap.get(ur.getRoleId()).getName());
            authorizes.add(authorize);
        });
        model.addAttribute("authroizes",authorizes);
        return "authorize/authorize_list";
    }*/
    @RequestMapping(value = "/authorize_list", method = RequestMethod.POST)
    public String findAuthorizes(Model model) {
        List<UserRole> userRoles = userRoleRepository.findAll();

        Collection<Integer> userIds = new HashSet<>();
        Collection<Integer> roleIds = new HashSet<>();
        userRoles.forEach(ur -> {
            userIds.add(ur.getUserId());
            roleIds.add(ur.getRoleId());
        });
        Collection<User> users = userService.findUsersById(userIds);
        Collection<Role> roles = roleService.findRolesById(roleIds);

        Map<Integer, User> userMap = User.idEntityMap(users);
        Map<Integer, Role> roleMap = Role.idEntityMap(roles);

        List<Authorize> authorizes = new LinkedList<>();
        userRoles.forEach(ur -> {
            Authorize authorize = new Authorize();
            authorize.setUserRoleId(ur.getId());
            authorize.setUserId(ur.getUserId());
            authorize.setUserName(userMap.get(ur.getUserId()).getName());
            authorize.setRoleId(ur.getRoleId());
            authorize.setRoleName(roleMap.get(ur.getRoleId()).getName());
            authorizes.add(authorize);
        });
        model.addAttribute("authroizes",authorizes);
        return "authorize/authorize_list";
    }
    /**
     * 根据用户Id查询用户角色对应关系
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserRoleByUserId", method = RequestMethod.POST)
    public String getUserRoleByUserId(Integer userId, Model model) {
        UserRole userRole = userService.findUserRoleByUserId(userId);
        model.addAttribute("userRole",userRole);
        return "authorize/authorize_list";
    }

    /**
     * 设置权限
     *
     * @param user
     * @param roleIds
     * @return 操作结果
     */
    @RequestMapping(value = "/setAuthorize",method = RequestMethod.POST)
    public String setAuthorize(User user, String roleIds) {
        String[] temp = roleIds.split(",");
        Integer[] roleIdArray = new Integer[temp.length];
        for (int i = 0; i < roleIdArray.length; i++) {
            roleIdArray[i] = Integer.valueOf(temp[i]);
        }
        return "authorize/authorize_list";
    }
}
