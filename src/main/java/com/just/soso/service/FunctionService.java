package com.just.soso.service;

import com.just.soso.entity.Functions;
import com.just.soso.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/3/21.
 */
@Service
public class FunctionService {
    @Autowired
    private FunctionRepository functionRepository;

    /**
     * 增加功能（菜单）
     *
     * @param functions
     */
    public void addFunction(Functions functions) {
        functionRepository.save(functions);
    }

    /**
     * 根据Id更新url信息
     *
     * @param id
     * @param url
     */
    public void updateUrl(Integer id, String url) {
        Functions functions = functionRepository.findById(id);
        functions.setUrl(url);
        functionRepository.save(functions);
    }

    /**
     * 根据Id删除功能
     *
     * @param id
     */
    public void deleteFunctionById(Integer id) {
        functionRepository.deleteById(id);
    }

    /**
     * 查询指定父节点的子节点
     *
     * @param parentId         父节点
     * @return 功能集合
     */
    public List<Functions> findFunctions(Integer parentId) {
        return functionRepository.findByParentId(parentId);
    }

    /**
     * 查询全部功能信息
     *
     *
     * @return
     */
    public List<Functions> findAllFunction() {
        return functionRepository.findAll();
    }

}
