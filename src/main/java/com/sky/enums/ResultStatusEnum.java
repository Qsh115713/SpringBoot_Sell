package com.sky.enums;

import lombok.Getter;

@Getter
public enum ResultStatusEnum implements SuperEnum {
    SUCCESS(0, "成功"),
    FAILURE(400, "失败");

    private Integer code;

    private String msg;

    ResultStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
