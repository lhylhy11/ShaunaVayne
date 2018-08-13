package com.cf.controller;

import com.cf.domain.User;
import com.cf.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//相当于一个controller加上requsestBody,默认使用jackson编译json
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser.do")
    public Object getUser() {
        List<User> userList = userService.getUserInfo();
        return userList;
    }

    /*
     *分页插件实例.
     * */
    @RequestMapping("/getUser1.do")
    public Object getUser1() {
        List<User> userList = userService.getUserInfo1();
        return userList;
    }

    @RequestMapping("/user.do")
    public ModelAndView  user() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("mess","cuowu");
        return modelAndView;
    }
}
