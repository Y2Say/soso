package com.just.soso.controller;

import com.just.soso.entity.User;
import com.just.soso.repository.UserRepository;
import com.just.soso.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/**
 * Created by user on 2017/3/20.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    /*@ResponseBody
    @RequestMapping(value = "/findUserByPage",method = RequestMethod.GET)
    public Collection<User> findUsers(Integer page, Integer rows) {
        Page<User> userPage = userService.findUsers("desc",page,rows);
        return userPage.getContent();
    }*/

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "footer";
    }
    @RequestMapping(value = "/user_list",method = RequestMethod.GET)
    public String findUsers(Model model) {
        List<User> userList = userRepository.findAll();
        if(userList != null){
            model.addAttribute("users",userList);
        }
        return "user/user_list";
    }
    /**
     * 新增、修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/addOrEditUser",method = RequestMethod.POST)
    public String addEditUser(User user) {
            if(Objects.equals(null,user.getId())){
                userService.addUser(user);
            }else{
                userService.updateUser(user);
            }

        return "user/user_list";
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUserById",method = RequestMethod.POST)
    public String deleteUser(Integer id) {
        userService.deleteUserById(id);
        return "user/user_list";
    }

}
