package com.wucc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wucc.service.MPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    HttpServletRequest req;
    @Autowired
    MPostService mPostService;


    public Page getPage() {
        int pn = ServletRequestUtils.getIntParameter(req, "pn", 1);
        int size = ServletRequestUtils.getIntParameter(req, "size", 2);
        return new Page(pn, size);
    }


}
