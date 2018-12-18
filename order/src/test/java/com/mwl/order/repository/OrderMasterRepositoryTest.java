package com.mwl.order.repository;

import com.mwl.order.OrderApplicationTests;
import com.mwl.order.bean.OrderMaster;
import com.mwl.order.enums.OrderStatusEnum;
import com.mwl.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {
    @Autowired
    private OrderMasterRepository masterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("八戒");
        orderMaster.setBuyerPhone("18888881111");
        orderMaster.setBuyerAddress("中国");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(3.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster result = masterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }
}