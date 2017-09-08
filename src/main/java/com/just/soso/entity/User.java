package com.just.soso.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/3/20.
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 7713455808967861329L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public static <T extends User> Map<Integer, T> idEntityMap(Collection<T> list) {
        Map<Integer, T> map = new HashMap<>();
        if (null == list || list.isEmpty()) {
            return map;
        }
        for (T entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }
}
