package com.wucc.mail;

import com.wucc.config.RabbitConfig;
import com.wucc.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQSenderTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("wxd.direct","queue1","hhhh");
    }
    @Test
    public void sendToQueueWxd(){
        String message = "收到请回复！";
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_WXD,RabbitConfig.ROUTINGKEY_WXD,message);
    }

}
