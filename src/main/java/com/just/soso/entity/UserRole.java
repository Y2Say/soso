package com.just.soso.entity;

import javax.persistence.*;

/**
 * Created by user on 2017/3/21.
 */
@Entity
@Table(name = "user_role")
@NamedQuery(name = "UserRole.findAll", query = "SELECT ur FROM UserRole ur")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
