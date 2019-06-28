package com.sky.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sky.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目表
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {
    //类目id
    @Id
    private String categoryId;

    //类目名字
    private String categoryName;

    //类目编号
    private String categoryType;

    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, String categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
