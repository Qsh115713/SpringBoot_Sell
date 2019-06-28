package com.sky.repository;

import com.sky.dataobject.SellerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerDetailRepository extends JpaRepository<SellerDetail, String> {
    SellerDetail findAllByOpenid(String openid);
}
