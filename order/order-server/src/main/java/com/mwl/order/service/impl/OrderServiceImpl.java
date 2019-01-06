package com.mwl.order.service.impl;

import com.mwl.order.bean.OrderDetail;
import com.mwl.order.bean.OrderMaster;
import com.mwl.order.dto.OrderDTO;
import com.mwl.order.enums.OrderStatusEnum;
import com.mwl.order.enums.PayStatusEnum;
import com.mwl.order.enums.ResultEnum;
import com.mwl.order.exception.OrderException;
import com.mwl.order.repository.OrderDetailRepository;
import com.mwl.order.repository.OrderMasterRepository;
import com.mwl.order.service.OrderService;
import com.mwl.order.utils.KeyUtil;
import com.mwl.product.client.ProductClient;
import com.mwl.product.common.DecreaseStockInput;
import com.mwl.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        //查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO
                .getOrderDetailList()
                .stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        // TODO
        //读redis（库存保存在其中）
        //减库存并将新值设置到redis
        //订单入库异常手动回滚redis

        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo
                            .getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    detailRepository.save(orderDetail);
                }
            }
        }

        //扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputList = orderDTO
                .getOrderDetailList()
                .stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5.2));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        masterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        Optional<OrderMaster> orderMasterOptional= masterRepository.findById(orderId);
        if(!orderMasterOptional.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if(OrderStatusEnum.NEW.getCode()!=orderMaster.getOrderStatus()){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        masterRepository.save(orderMaster);
        //构造OrderDTO
        List<OrderDetail> orderDetails = detailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetails)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
