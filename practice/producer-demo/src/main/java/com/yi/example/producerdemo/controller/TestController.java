package com.yi.example.producerdemo.controller;

import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.*;

/**
 * @author : xiao on 2022/8/24 18:12
 * @version : 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController implements ApplicationContextAware {


    private ApplicationContext applicationContext;


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
//        SendResult sendResult = rocketMQTemplate.syncSend("send-topic", message);
        GenericMessage<String> stringGenericMessage = new GenericMessage<>(message);
        String arg = "";
        if(StringUtils.isNotBlank(message)){
            arg = message.substring(0, 1);
        }
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction("send-transaction-topic", stringGenericMessage, arg);
        String s = result.getLocalTransactionState().name();
        String name = result.getSendStatus().name();
        return ResponseEntity.ok("执行事务消息结果：local：" + s + ", sendStatus:" + name);
    }


    @GetMapping("/t/{id}")
    public ResponseEntity<String> test(@PathVariable String id) {


        SendResult aaaaaa = rocketMQTemplate.syncSendOrderly("send-orderly-topic", "aaaaaa", id);

        Object userServiceImpl = applicationContext.getBean("userServiceImpl");
        Class<?> aClass = AopProxyUtils.ultimateTargetClass(userServiceImpl);
        System.out.println(aClass.getName());
        return ResponseEntity.ok(aClass.getName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
