package com.fc.controller;

import com.fc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("login")
    public ModelAndView login(String username, String password){
        return userService.login(username, password);
    }

}
