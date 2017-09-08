package com.just.soso;

/**
 * Created by So on 2017/3/23.
 * ClassName RoleEnum
 */
public enum RoleEnum {
    ADMIN(1,"admin"),USER(2,"user");
    Integer id;
    String name;

    RoleEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
