package com.wucc.controller;

import com.wucc.entity.Message;
import com.wucc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2020-07-01 12:10:18
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    /**
     * 服务对象
     */
    @Autowired
    private MessageService messageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Message selectOne(String id) {
        return messageService.queryById(id);
    }

}