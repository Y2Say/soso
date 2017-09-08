package com.just.soso.controller;

import com.just.soso.entity.User;
import com.just.soso.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by So on 2017/3/22.
 * ClassName LoginControllerTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {
    private UserRepository userRepository;
   /* @Test
    public void add() {
        User u = new User();
        u.setName("jyj");
        u.setPassword("123123");
        userRepository.save(u);
    }*/
   /*@Test
   public void find(){
       User user = userRepository.findById(1);
       System.out.print(user.getId()+"222222222"+user.getPassword()+"3333333333"+user.getName());
   }*/
}
