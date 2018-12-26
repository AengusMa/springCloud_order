package com.mwl.order.message;

import com.mwl.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author mawenlong
 * @date 2018/12/25
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.OUTPUT)
    public void process(Message<OrderDTO> message) {
        log.info("outputMessage---->StreamReceiver:{}", message.getPayload().getBuyerName());
    }

    @StreamListener(StreamClient.INPUT)
    public void processInput(Message<String> message) {
        log.info("inputMessage---->StreamReceiver:{}", message.getPayload());
    }
}
