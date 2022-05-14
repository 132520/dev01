package com.fc.service.impl;

import com.fc.dao.TbUserMapper;
import com.fc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;


    @Override
    public ModelAndView login(String username, String password) {


        return null;
    }
}
