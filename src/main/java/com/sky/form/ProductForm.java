package com.sky.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class ProductForm {

    private String productId;

    //名称
    @NotEmpty
    private String productName;

    //单价
    @NotEmpty
    private BigDecimal productPrice;

    //库存
    @NotEmpty
    private Integer productStock;

    //描述
    private String productDescription;

    //小图
    private String productIcon;

    //类目编号
    @NotEmpty
    private String categoryType;
}
