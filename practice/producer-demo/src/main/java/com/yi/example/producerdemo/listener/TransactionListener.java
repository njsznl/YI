package com.yi.example.producerdemo.listener;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author : xiao on 2022/8/27 2:17
 * @version : 1.0
 */
@Component
@RocketMQTransactionListener
public class TransactionListener implements RocketMQLocalTransactionListener {

    private volatile boolean tr = false;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        tr = false;
        if (arg != null) {
            int integer = Integer.parseInt(String.valueOf(arg));
            if (integer > 4) {
                tr = true;
                return RocketMQLocalTransactionState.UNKNOWN;
            }
        }

        System.out.println("执行本地事务流程");
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {

        if (tr) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        System.out.println("检查本地事务流程");
        return RocketMQLocalTransactionState.COMMIT;
    }
}
