package com.just.soso.service;


import com.just.soso.entity.User;
import com.just.soso.entity.UserRole;
import com.just.soso.repository.UserRepository;
import com.just.soso.repository.UserRoleRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by user on 2017/3/21.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    public User findByName(String name){
        return userRepository.findByName(name);
    }
    /**
     * 保存用户信息
     *
     * @param user
     */
    public void addUser(User user) {

        userRepository.save(user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void updateUser(User user) {
        userRepository.save(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * 分页查询用户信息
     *
     * @param page
     * @param size
     * @return
     */
    public Page<User> findUsers(String orderType, int page, int size) {
        Sort sort = new Sort(Sort.Direction.ASC);
        if ("desc".equalsIgnoreCase(orderType)) {
            sort = new Sort(Sort.Direction.DESC);
        }
        Pageable pageable = new PageRequest(page < 0 ? 0 : page, size < 0 ? 20 : size, sort);
        Page<User> users = this.userRepository.findAll(pageable);
        if (null == users) {
            throw new ServiceException("exception.user.user_not_found");
        }
        return users;
    }

    /**
     * 根据ID集合查询对应的用户信息
     *
     * @param ids
     * @return
     */
    public Collection<User> findUsersById(Collection<Integer> ids) {
        Collection<User> userCollection = new ArrayList<>();
        ids.forEach(id -> {
            User user = userRepository.findById(id);
            userCollection.add(user);
        });
        return userCollection;
    }

    /**
     * 分页查询用户角色对应关系集合
     *
     * @param page
     * @param size
     * @return
     */
    public Page<UserRole> findUserRoles(String orderType, int page, int size) {

        Sort sort = new Sort(Sort.Direction.ASC);
        if ("desc".equalsIgnoreCase(orderType)) {
            sort = new Sort(Sort.Direction.DESC);
        }
        Pageable pageable = new PageRequest(page < 0 ? 0 : page, size < 0 ? 20 : size, sort);
        Page<UserRole> userRoles = this.userRoleRepository.findAll(pageable);
        if (null == userRoles) {
            throw new ServiceException("exception.userrole.userrole_not_found");
        }
        return userRoles;
    }

    /**
     * 根据用户ID查询用户角色对应关系
     *
     * @param userId
     * @return
     */
    public UserRole findUserRoleByUserId(Integer userId) {
        return userRoleRepository.findUserRoleByUserId(userId);
    }
    /**
     * 保存用户角色对应关系
     *
     * @param userId
     * @param roleIds 用户对应的角色
     */
    public void addUserRoles(Integer userId, Integer[] roleIds) {
        List<UserRole> userRoles = new ArrayList<>();
        Arrays.asList(roleIds).forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoles.add(userRole);
        });
        userRoleRepository.save(userRoles);
    }
}
