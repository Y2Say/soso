package com.just.soso.controller;

import com.just.soso.common.AjaxResult;
import com.just.soso.entity.Functions;
import com.just.soso.service.FunctionService;

import com.just.soso.service.NativeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * Created by So on 2017/3/22.
 */
@Controller
public class FunctionController {
    @Autowired
    private FunctionService functionService;
    @Autowired
    private NativeCache nativeCache;

    @RequestMapping(value = "function_list",method = RequestMethod.GET)
    public String findAll(Model model){
        List<Functions> functionsList = functionService.findAllFunction();
        if(functionsList.size()>0){
            model.addAttribute("functions",functionsList);
        }
        return "function/function_list";
    }

    /**
     * 新增、修改功能
     *
     * @param functions
     * @return
     */
    @RequestMapping(value = "/addEditFunction", method = RequestMethod.POST)
    public String addEditFunction(Functions functions) {

        if (Objects.equals(null,functions.getId())) {
            functions.setSerialNum(nativeCache.getFunctions().size());
            functionService.addFunction(functions);
            nativeCache.addFunction(functions);
        } else {
            functionService.updateUrl(functions.getId(), functions.getUrl());
            nativeCache.replaceFunction(functions);
        }

        return "function/function_list";
    }

    /**
     * 根据Id删除功能
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteFunction", method = RequestMethod.POST)
    public String deleteFunction(Integer id) {

        functionService.deleteFunctionById(id);
        nativeCache.removeFunction(id);

        return "function/function_list";
    }

    /**
     * 查询子功能信息集合
     *
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/findFunction", method = RequestMethod.GET)
    public String findFunctions(Integer parentId, Model model) {
        if (Objects.equals(null, parentId)) {
            parentId = 0;
        }
        List<Functions> functionsList = functionService.findFunctions(parentId);
        model.addAttribute("functionList",functionsList);
        return "function/function_list";
    }
}
