package com.sky.service;

import com.sky.dataobject.SellerDetail;

public interface SellerService {

    SellerDetail findSellerDetailByOpenid(String openid);
}
