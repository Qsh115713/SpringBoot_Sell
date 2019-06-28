package com.sky.service;

import com.sky.dataobject.SellerDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceTest {

    private SellerService service;

    @Autowired
    public void setService(SellerService service) {
        this.service = service;
    }

    @Test
    public void findSellerDetailByOpenid() {
        SellerDetail sellerDetail = service.findSellerDetailByOpenid("abc");
        Assert.assertEquals("admin", sellerDetail.getUserName());
    }
}