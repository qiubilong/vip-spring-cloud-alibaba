package com.tuling.tccorderservice.controller;

import com.tuling.datasource.entity.Order;
import com.tuling.datasource.vo.ResultVo;
import com.tuling.tccorderservice.service.BussinessService;
import com.tuling.tccorderservice.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    

    @Autowired
    private BussinessService bussinessService;

    @PostMapping("/createOrder")
    public ResultVo createOrder(@RequestBody OrderVo orderVo) throws Exception {
        log.info("收到下单请求,用户:{}, 商品编号:{}", orderVo.getUserId(), orderVo.getCommodityCode());
        Order order = bussinessService.saveOrder(orderVo);
        return ResultVo.ok().put("order",order);
    }
    
}
