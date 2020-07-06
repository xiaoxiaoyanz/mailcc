package com.wucc.controller;

import com.wucc.entity.User;
import com.wucc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-07-01 12:10:19
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    //@GetMapping("/selectOne")
    @RequestMapping(value = "/selectOne", method = RequestMethod.GET)
    public User selectOne(String id) {
        System.out.println("cc");
        return userService.queryById(id);
    }
/*
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/user/login";
    }*/




}