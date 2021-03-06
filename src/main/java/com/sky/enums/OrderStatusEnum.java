package com.sky.enums;

import lombok.Getter;

//订单状态
@Getter
public enum OrderStatusEnum implements SuperEnum {
    NEW(0, "新订单"),
    FINISH(1, "已完结"),
    CANCEL(2, "已取消");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
