package com.sky.dataobject.mapper;

import com.sky.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_id, category_name, category_type) values(#{category_id, jdbcType=VARCHAR}, #{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_id, category_name, category_type) values(#{categoryId, jdbcType=VARCHAR}, #{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=VARCHAR})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({  //Select需要添加这个！！！
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(String categoryType);

    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({  //Select需要添加这个！！！
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,
                             @Param("categoryType") String categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_id = #{categoryId}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_id = #{categoryId}")
    int deleteByCategoryId(String categoryId);

    ProductCategory selectByCategoryType(String categoryType);
}


