package com.yi.example.rocketmqconsumerdemo.consumer;

import com.yi.example.rocketmqconsumerdemo.bean.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author : xiao on 2022/8/26 21:30
 * @version : 1.0
 */
@Service
@RocketMQMessageListener(topic = "user-topic", consumerGroup = "user-group")
public class UserConsumer implements RocketMQListener<User> {
    @Override
    public void onMessage(User user) {
        System.out.println(user.toString());
    }
}
