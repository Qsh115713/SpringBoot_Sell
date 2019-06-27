package com.sky.repository;

import com.sky.dataobject.ProductDetail;
import com.sky.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDetailRepositoryTest {

    @Autowired
    private ProductDetailRepository repository;

    @Test
    public void saveTest() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId("2");
        productDetail.setProductName("八宝粥");
        productDetail.setProductPrice(new BigDecimal(1.3));
        productDetail.setProductStock(100);
        productDetail.setProductDescription("好喝！！！");
        productDetail.setProductIcon("http://xxxxx.jpg");
        productDetail.setProductStatus(0);
        productDetail.setCategoryType("2");

        ProductDetail result = repository.save(productDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception {

        List<ProductDetail> productDetails = repository.findByProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotEquals(0, productDetails.size());
    }

}