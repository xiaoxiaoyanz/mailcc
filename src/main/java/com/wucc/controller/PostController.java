package com.wucc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController extends BaseController{

    @GetMapping("/category/{id:\\d*}")
    public String category(@PathVariable(name = "id") Long id) {
        req.setAttribute("currentCategoryId",id);
        return "post/category";
    }

    @GetMapping("/post/{id:\\d*}")
    public String detail(@PathVariable(name = "id") Long id) {

        return "post/detail";
    }



}
