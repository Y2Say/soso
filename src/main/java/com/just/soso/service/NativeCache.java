package com.just.soso.service;

import com.just.soso.entity.Functions;
import com.just.soso.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by So on 2017/3/22.
 * ClassName NativeCache
 */
@Service
public class NativeCache {
    private Map<Integer, Functions> functionMap = new HashMap<>();
    private Map<Integer, List<Role>> userRoleMap = new HashMap<>();
    @Autowired
    private FunctionService functionService;

    @PostConstruct
    public void init() {
        List<Functions> functionsList = functionService.findAllFunction();
        functionsList.forEach(functions -> functionMap.put(functions.getId(), functions));
    }

    public List<Functions> getFunctions() {
        if (functionMap.isEmpty()) {
            init();
        }
        return new ArrayList<>(functionMap.values());
    }

    public void removeFunction(Integer functionId) {
        if (functionMap.containsKey(functionId)) {
            functionMap.remove(functionId);
        }
    }

    public void addFunction(Functions functions) {
        functionMap.put(functions.getId(), functions);
    }

    public void replaceFunction(Functions functions) {
        if (functionMap.containsKey(functions.getId())) {
            functionMap.remove(functions.getId());
            functionMap.put(functions.getId(), functions);
        }
    }

    public void setRoles(Integer userId, List<Role> roles) {
        userRoleMap.put(userId, roles);
    }

    public List<Role> getRoles(Integer userId) {
        return userRoleMap.get(userId);
    }

    public Functions getFunction(Integer id) {
        return functionMap.get(id);
    }
}
