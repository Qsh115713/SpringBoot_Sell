package com.sky.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sky.enums.ProductStatusEnum;
import com.sky.utils.EnumUtil;
import com.sky.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品详情
 */
@Entity
@Data
@DynamicUpdate
public class ProductDetail implements Serializable {

    private static final long serialVersionUID = -3442264162679815320L;

    @Id
    private String productId;

    //名称
    private String productName;

    //单价
    private BigDecimal productPrice;

    //库存
    private Integer productStock;

    //描述
    private String productDescription;

    //小图
    private String productIcon;

    //状态：0 正常；1 下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    //类目编号
    private String categoryType;

    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @JsonIgnore
    public String getProductStatusMsg() {
        return EnumUtil.getMsgByCode(productStatus, ProductStatusEnum.class);
    }
}
