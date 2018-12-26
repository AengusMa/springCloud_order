package com.mwl.order.controller;

import com.mwl.order.dto.OrderDTO;
import com.mwl.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author mawenlong
 * @date 2018/12/25
 */
@RestController
public class SendMessageController {
    @Autowired
    private StreamClient streamClient;

    @GetMapping("sendMessage")
    public void process() {
        String message = "date:" + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }
    @GetMapping("sendObject")
    public void sendObeject() {
        OrderDTO orderDTO= new OrderDTO();
        orderDTO.setBuyerName("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
