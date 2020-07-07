package com.wucc.controller;

import com.wucc.entity.User;
import com.wucc.pub.util.MailException;
import com.wucc.pub.util.Response;
import com.wucc.pub.util.ResponseUtil;
import com.wucc.pub.util.Status;
import com.wucc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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




    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest req, HttpServletResponse resp){
        String errorMsg = "";
        try{
            errorMsg = userService.login(req,resp);
        }catch (Exception e){
            errorMsg = "登陆失败，服务端异常！" + e.getMessage();
        }
        return ResponseUtil.buildResponse(errorMsg, Status.fail,null);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(HttpServletRequest req, HttpServletResponse resp){
        String errorMsg = "";
        try{
            userService.register(req,resp);
        }catch (MailException e){
            errorMsg = "注册失败！" + e.getMessage();
        }catch (Exception e){
            errorMsg = "注册失败，服务端异常！" + e.getMessage();
        }
        return ResponseUtil.buildResponse(errorMsg,Status.fail,null);
    }

}
