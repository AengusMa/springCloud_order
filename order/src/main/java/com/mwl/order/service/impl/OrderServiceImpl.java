package com.mwl.order.service.impl;

import com.mwl.order.bean.OrderMaster;
import com.mwl.order.dto.OrderDTO;
import com.mwl.order.enums.OrderStatusEnum;
import com.mwl.order.enums.PayStatusEnum;
import com.mwl.order.repository.OrderDetailRepository;
import com.mwl.order.repository.OrderMasterRepository;
import com.mwl.order.service.OrderService;
import com.mwl.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author mawenlong
 * @date 2018/12/18
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository detailRepository;
    @Autowired
    private OrderMasterRepository masterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        // TODO 查询商品信息

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5.2));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        masterRepository.save(orderMaster);
        return orderDTO;
    }
}
