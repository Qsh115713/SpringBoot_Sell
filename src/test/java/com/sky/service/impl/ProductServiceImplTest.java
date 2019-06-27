package com.sky.service.impl;

import com.sky.dataobject.ProductDetail;
import com.sky.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    private ProductServiceImpl service;

    @Autowired
    public void setService(ProductServiceImpl service) {
        this.service = service;
    }

    @Test
    public void findByProductId() {
        ProductDetail productDetail = service.findByProductId("123456");
        Assert.assertEquals("123456", productDetail.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductDetail> lists = service.findUpList();
        Assert.assertNotEquals(0, lists.size());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(1, 2);
        Page<ProductDetail> productInfoPage = service.findList(request);
        for (ProductDetail item : productInfoPage) {
            System.out.println(item.toString());
        }
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId("123459");
        productDetail.setProductName("黑米粥");
        productDetail.setProductPrice(new BigDecimal(11.8));
        productDetail.setProductStock(100);
        productDetail.setProductDescription("好喝！！！");
        productDetail.setProductIcon("http://xxxxx.jpg");
        productDetail.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productDetail.setCategoryType("3");
        ProductDetail result = service.save(productDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void onSale() {
        String productId = "101";
        service.onSale(productId);
    }

    @Test
    public void offSale() {
        String productId = "101";
        service.offSale(productId);
    }
}