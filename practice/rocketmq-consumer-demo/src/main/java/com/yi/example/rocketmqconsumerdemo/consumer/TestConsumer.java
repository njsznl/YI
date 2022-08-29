package com.yi.example.rocketmqconsumerdemo.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author : xiao on 2022/8/24 18:45
 * @version : 1.0
 */
@Service
@RocketMQMessageListener(topic = "send-topic", consumerGroup = "test-consumer")
public class TestConsumer implements RocketMQListener<String> {

    private Object o = new Object();


    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }
}
