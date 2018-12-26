package com.mwl.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author mawenlong
 * @date 2018/12/25
 */
public interface StreamClient {

    String INPUT = "inputMessage";
    String OUTPUT = "outputMessage";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();
}
