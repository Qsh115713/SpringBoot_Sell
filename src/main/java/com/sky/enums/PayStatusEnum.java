package com.sky.enums;

import lombok.Getter;

//订单状态
@Getter
public enum PayStatusEnum implements SuperEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
