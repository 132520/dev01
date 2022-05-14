package com.fc.service;

import org.springframework.web.servlet.ModelAndView;

public interface UserService {
    ModelAndView login(String username, String password);
}
