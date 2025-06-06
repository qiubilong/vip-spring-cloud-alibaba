package com.tuling.tccorderservice.service.impl;

import com.tuling.datasource.entity.Order;
import com.tuling.datasource.entity.OrderStatus;
import com.tuling.datasource.mapper.OrderMapper;
import com.tuling.tccorderservice.service.OrderService;
import com.tuling.tccorderservice.vo.OrderVo;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Fox
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Order prepareSaveOrder(OrderVo orderVo,
                                  @BusinessActionContextParameter(paramName = "orderId") Long orderId) {

        // 保存订单
        Order order = new Order();
        order.setId(orderId);
        order.setUserId(orderVo.getUserId());
        order.setCommodityCode(orderVo.getCommodityCode());
        order.setCount(orderVo.getCount());
        order.setMoney(orderVo.getMoney());
        order.setStatus(OrderStatus.INIT.getValue());
        Integer saveOrderRecord = orderMapper.insert(order);
        log.info("保存订单{}", saveOrderRecord > 0 ? "成功" : "失败");

        return order;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {


        // 获取订单id
        long orderId = Long.parseLong(actionContext.getActionContext("orderId").toString());
        //更新订单状态为支付成功
        Integer updateOrderRecord = orderMapper.updateOrderStatus(orderId,OrderStatus.SUCCESS.getValue());
        log.info("更新订单id:{} {}", orderId, updateOrderRecord > 0 ? "成功" : "失败");

        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {

        //获取订单id
        long orderId = Long.parseLong(actionContext.getActionContext("orderId").toString());
        //更新订单状态为支付失败
        Integer updateOrderRecord = orderMapper.updateOrderStatus(orderId,OrderStatus.FAIL.getValue());
        log.info("更新订单id:{} {}", orderId, updateOrderRecord > 0 ? "成功" : "失败");

        return true;
    }


}
