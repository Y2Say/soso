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
@Table(name="role")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {

    private static final long serialVersionUID = 3557615564704715389L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String functionIds;

    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

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

    public Role() {
    }

    public static <T extends Role> Map<Integer, T> idEntityMap(Collection<T> list) {
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
