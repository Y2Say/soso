package com.just.soso.controller;

import com.just.soso.entity.Functions;
import com.just.soso.entity.RoleFunction;
import com.just.soso.entity.User;
import com.just.soso.entity.UserRole;
import com.just.soso.repository.FunctionRepository;
import com.just.soso.repository.UserRepository;

import com.just.soso.service.RoleService;
import com.just.soso.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 2017/3/21.
 */
@Controller
@RequestMapping("/")
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class.getName());
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FunctionRepository functionRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/registerpage", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String name, String password,Model model) {
        User user = userService.findByName(name);
        if (user != null && password.equals(user.getPassword())) {
            UserRole ur = userService.findUserRoleByUserId(user.getId());
            List<RoleFunction> rfs = roleService.findRoleFunctions(ur.getRoleId());
            List<Functions> functionsList = new ArrayList<>();
            rfs.forEach(rf->{
                Functions functions = functionRepository.findById(rf.getId());
                functionsList.add(functions);

            });
            model.addAttribute("functions",functionsList);
            model.addAttribute("name",name);
            return "main";
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user) throws Exception {
        logger.info(user.getName() + "11111111111" + user.getPassword());
        if ( user == null) {
            logger.info(user.getName() + "2222222222222" + user.getPassword());
            throw new Exception("exception.user.isnull");
        }
        User u = new User();
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        logger.info(u+"------------------------");
        userService.addUser(u);
        logger.info(user.getName() + "2222222222222" + user.getPassword());
        return "index";
    }
}
