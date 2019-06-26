package com.sky.service;

import com.sky.dto.OrderDTO;

public interface BuyerService {
    //查询单个订单
    OrderDTO findOrder(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
