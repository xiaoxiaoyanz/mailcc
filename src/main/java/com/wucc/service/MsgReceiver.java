package com.wucc.service;

import com.wucc.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MsgReceiver {

    //@RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void receive1(String content){
        log.info("接收处理队列A当中的消息：" + content);
    }

    //@RabbitListener(queues = "laowang.haha")
    public void receive2(String str){
        System.out.println("接收消息"+ str);
    }

    //@RabbitListener(queues = "queue1")
    public void receive3(String str){
        System.out.println("接收消息"+ str);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_WXD)
    public void receive4(String str){
        System.out.println("收到" + str);
    }
}
