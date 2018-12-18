package com.mwl.order.converter;


import com.google.gson.Gson;
import com.mwl.order.bean.OrderDetail;
import com.mwl.order.dto.OrderDTO;
import com.mwl.order.form.ItemForm;
import com.mwl.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mawenlong
 * @date 2018/12/18
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        // try {
        //     orderDetailList = gson.fromJson(orderForm.getItems(),
        //                                     new TypeToken<List<OrderDetail>>() {
        //                                     }.getType());
        // } catch (Exception e) {
        //     log.error("【json转换】错误, string={}", orderForm.getItems());
        //     throw new OrderException(ResultEnum.PARAM_ERROR);
        // }
        //
        for (ItemForm itemForm : orderForm.getItems()) {
            OrderDetail detail = new OrderDetail();
            detail.setProductId(itemForm.getProductId());
            detail.setProductQuantity(itemForm.getProductQuantity());
            orderDetailList.add(detail);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
