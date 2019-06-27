package com.sky.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sky.dataobject.OrderDetail;
import com.sky.enums.OrderStatusEnum;
import com.sky.enums.PayStatusEnum;
import com.sky.utils.EnumUtil;
import com.sky.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用于数据传输
 */

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    //订单ID
    private String orderId;

    //买家名字
    private String buyerName;

    //买家电话
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家微信openid
    private String buyerOpenid;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态，默认为新下单。
    private Integer orderStatus;

    //支付状态，默认为未支付。
    private Integer payStatus;

    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    //订单详情
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public String getOrderStatusMsg() {
        return EnumUtil.getMsgByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public String getPayStatusMsg() {
        return EnumUtil.getMsgByCode(payStatus, PayStatusEnum.class);
    }
}
