package com.sky.service.impl;

import com.sky.dataobject.SellerDetail;
import com.sky.repository.SellerDetailRepository;
import com.sky.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    private SellerDetailRepository repository;

    @Autowired
    public void setRepository(SellerDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public SellerDetail findSellerDetailByOpenid(String openid) {
        return repository.findAllByOpenid(openid);
    }
}
