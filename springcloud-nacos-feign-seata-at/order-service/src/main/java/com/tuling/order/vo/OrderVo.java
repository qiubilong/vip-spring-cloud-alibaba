package com.tuling.order.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author Fox
 */
@Data
public class OrderVo {
    private String userId;
    /**商品编号**/
    private String commodityCode;
    
    private Integer count;
    
    private Integer money;

    public static void main(String[] args) {
        OrderVo vo = new OrderVo();
        vo.setUserId(3344+"");
        vo.setCommodityCode("111");
        vo.setCount(1);
        vo.setMoney(560);
        System.out.println(JSONObject.toJSONString(vo));

    }
}
