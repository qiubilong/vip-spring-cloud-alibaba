package com.tuling.tccorderservice.service.impl;

import com.tuling.datasource.entity.Order;
import com.tuling.tccorderservice.feign.AccountFeignService;
import com.tuling.tccorderservice.feign.StorageFeignService;
import com.tuling.tccorderservice.service.BussinessService;
import com.tuling.tccorderservice.service.OrderService;
import com.tuling.tccorderservice.util.UUIDGenerator;
import com.tuling.tccorderservice.vo.OrderVo;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Fox
 */
@Service
@Slf4j
public class BusinessServiceImpl implements BussinessService {

    @Autowired
    private AccountFeignService accountFeignService;

    @Autowired
    private StorageFeignService storageFeignService;

    @Autowired
    private OrderService orderService;


    @Override
    @GlobalTransactional(name="createOrder",rollbackFor=Exception.class)
    public Order saveOrder(OrderVo orderVo) {
        log.info("=============用户下单=================");
        log.info("当前 XID: {}", RootContext.getXID());

        //获取全局唯一订单号  测试使用
        Long orderId = UUIDGenerator.generateUUID();

        //阶段一： 创建订单
        Order order = orderService.prepareSaveOrder(orderVo,orderId);

        //扣减库存
        storageFeignService.deduct(orderVo.getCommodityCode(), orderVo.getCount());
        //扣减余额
        accountFeignService.debit(orderVo.getUserId(), orderVo.getMoney());

        return order;
    }
}
