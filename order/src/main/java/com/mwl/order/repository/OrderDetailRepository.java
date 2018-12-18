package com.mwl.order.repository;

import com.mwl.order.bean.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mawenlong
 * @date 2018/12/18
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
