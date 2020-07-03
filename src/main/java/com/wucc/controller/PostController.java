package com.wucc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @GetMapping("/category/{id:\\d*}")
    public String category(@PathVariable(name = "id") Long id) {


        return "category";
    }

    @GetMapping("/detail/{id:\\d*}")
    public String detail(@PathVariable(name = "id") Long id) {

        return "post/detail";
    }



}
