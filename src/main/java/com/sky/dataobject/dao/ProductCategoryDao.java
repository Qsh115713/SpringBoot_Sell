package com.sky.dataobject.dao;

import com.sky.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ProductCategoryDao {

    private ProductCategoryMapper mapper;

    @Autowired
    public void setMapper(ProductCategoryMapper mapper) {
        this.mapper = mapper;
    }

    public int insertByMap(Map<String, Object> map) {
        return mapper.insertByMap(map);
    }
}
