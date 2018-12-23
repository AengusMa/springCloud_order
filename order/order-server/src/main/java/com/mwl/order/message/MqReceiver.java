package com.mwl.order.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mawenlong
 * @date 2018/12/23
 */
@Component
public class MqReceiver {

    private static Log logger = LogFactory.getLog(MqReceiver.class);

    // @RabbitListener(queues = "myQueue")
    //自动创建注解
    // @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //自动创建并绑定ExChange和Queue
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        logger.info("myReceiver:" + message);
    }
    /**
     * 数码供应商
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerQueue")
    ))
    public void processComputer(String message) {
        logger.info("computerQueue-->mqReceiver: " + message);
    }
    /**
     * 水果供应商
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitQueue")
    ))
    public void processFruit(String message) {
        logger.info("fruitQueue-->mqReceiver: " + message);
    }
}
