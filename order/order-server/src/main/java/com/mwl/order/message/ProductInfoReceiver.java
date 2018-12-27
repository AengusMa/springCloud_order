package com.mwl.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mwl.order.utils.JsonUtil;
import com.mwl.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author mawenlong
 * @date 2018/12/26
 */
@Component
@Slf4j
@Transactional
public class ProductInfoReceiver {

    public static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // JsonUtil.f
        List<ProductInfoOutput> outputList =
                (List<ProductInfoOutput>) JsonUtil.fromJson(message, new TypeReference<List<ProductInfoOutput>>() {
                });
        //    接受到消息存储到redis
        for (ProductInfoOutput output : outputList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, output.getProductId()),
                                                  String.valueOf(output.getProductStock()));
        }
    }
}
