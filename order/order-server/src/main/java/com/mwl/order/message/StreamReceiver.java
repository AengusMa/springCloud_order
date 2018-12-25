package com.mwl.order.message;

import com.netflix.eureka.util.MeasuredRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author mawenlong
 * @date 2018/12/25
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener("myMessage")
    public void process(Object message) {
      log.info("StreamReceiver:{}" , message);
    }

}
