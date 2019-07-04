package com.sky.service;

public interface SecKillService {

    /**
     * 查询秒杀活动特价商品的信息
     */
    String querySecKillProductDetail(String productId);

    /**
     * 模拟不同用户秒杀同一商品的请求
     */
    void orderProductMockDiffUser(String productId);

}
