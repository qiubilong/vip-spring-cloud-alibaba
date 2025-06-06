package com.tuling.tccorderservice.service;

import com.tuling.datasource.entity.Order;
import com.tuling.tccorderservice.vo.OrderVo;

/**
 * @Author Fox
 */
public interface BussinessService {

    /**
     * 保存订单
     */
    Order saveOrder(OrderVo orderVo) ;
}
