package com.sky.service;

import com.sky.dataobject.ProductCategory;

import java.util.List;
import java.util.Map;

/**
 * 类目
 */
public interface CategoryService {

    ProductCategory findByCategoryId(Integer categoryId);

    List<ProductCategory> findList();

    Map<String, String> findCategoryMap();

    List<ProductCategory> findByCategoryTypeIn(List<String> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
