package com.yi.example.rocketmqconsumerdemo.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author : xiao on 2022/8/27 2:20
 * @version : 1.0
 */
@Service
@RocketMQMessageListener(topic = "send-transaction-topic", consumerGroup = "transaction")
public class TransactionConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {

        System.out.println("事务消费消息成功，收到：" + message);
    }
}
