package com.sky.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情表
 */

@Entity
@Data
public class OrderDetail {

    //订单详情ID
    @Id
    private String detailId;

    //订单ID
    private String orderId;

    //商品ID
    private String productId;

    //商品名称
    private String productName;

    //当前价格，单位：分 (不能从前端传过来，要从数据库里面取)
    private BigDecimal productPrice;

    //商品数量
    private Integer productQuantity;

    //小商品图
    private String productIcon;
}
