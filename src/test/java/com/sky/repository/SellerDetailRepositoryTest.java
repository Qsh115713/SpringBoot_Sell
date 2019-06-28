package com.sky.repository;

import com.sky.dataobject.SellerDetail;
import com.sky.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerDetailRepositoryTest {

    private SellerDetailRepository repository;

    @Autowired
    public void setSellerDetailRepository(SellerDetailRepository sellerDetailRepository) {
        this.repository = sellerDetailRepository;
    }

    @Test
    public void save() {
        SellerDetail sellerDetail = new SellerDetail();
        sellerDetail.setSellerId(KeyUtil.genUniqueKey());
        sellerDetail.setUserName("admin");
        sellerDetail.setUserPwd("123456");
        sellerDetail.setOpenid("abc");

        repository.save(sellerDetail);
    }

    @Test
    public void findAllByOpenid() {
        SellerDetail sellerDetail = repository.findAllByOpenid("abc");
        Assert.assertEquals("admin", sellerDetail.getUserName());
    }
}