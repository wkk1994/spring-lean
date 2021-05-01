package com.wkk.learn.spring.bean.scope.web.controller;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description 首页 controller
 * @Author Wangkunkun
 * @Date 2021/5/1 14:20
 */
@Controller
public class IndexController {

    @Autowired
    private User scopeUser;


    @Autowired
    private User user;

    @GetMapping("/index")
    public String index(Model model) {
        System.out.println(user);
        model.addAttribute("userObject", scopeUser);
        //model.addAttribute("user", user);
        return "index";
    }
}
