package com.sky.dataobject.mapper;

import com.sky.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    //需要在启动的主类上面配置一下扫描的路径
    private ProductCategoryMapper mapper;

    @Autowired
    public void setMapper(ProductCategoryMapper mapper) {
        this.mapper = mapper;
    }

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("category_id", "203");
        map.put("category_name", "折扣榜");
        map.put("category_type", "3");
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory("204", "折扣榜", "3");
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() throws Exception {
        ProductCategory productCategory = mapper.findByCategoryType("3");
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findByCategoryName() throws Exception {
        List<ProductCategory> productCategory = mapper.findByCategoryName("折扣榜");
        Assert.assertNotEquals(0, productCategory.size());
    }

    @Test
    public void updateByCategoryType() throws Exception {
        int result = mapper.updateByCategoryType("甩卖榜", "3");
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId("203");
        productCategory.setCategoryName("折扣榜");

        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryId() throws Exception {
        int result = mapper.deleteByCategoryId("204");
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByCategoryType() throws Exception {
        ProductCategory productCategory = mapper.selectByCategoryType("0");
        Assert.assertNotNull(productCategory);
    }
}