package com.tensquare.test;

import com.tensquare.rabbitmq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = rabbitmq.class)
public class product {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendmsg1(){
        rabbitTemplate.convertAndSend("itcast","I am ustcer");
    }

    /**
     * 分裂模式
     */
    @Test
    public void sendmsg2(){
        rabbitTemplate.convertAndSend("chuanzhi","","这是分裂模式");
    }

    /**
     * 主题模式
     */
    @Test
    public void sendmsg3(){
        rabbitTemplate.convertAndSend("topic84","good.log","这是主题模式");
    }
}
