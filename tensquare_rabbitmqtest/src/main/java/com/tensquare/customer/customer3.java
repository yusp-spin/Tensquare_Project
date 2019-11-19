package com.tensquare.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "kudingyu")
public class customer3 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("kudingyu:"+msg);

    }


}
