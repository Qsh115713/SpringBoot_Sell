package com.sky.service.impl;

import com.sky.dataobject.ProductCategory;
import com.sky.repository.ProductCategoryRepository;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类目
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private ProductCategoryRepository repository;

    @Autowired
    public void setRepository(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductCategory findByCategoryId(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findList() {
        return repository.findAll();
    }

    @Override
    public Map<String, String> findCategoryMap() {
        return findList().stream().collect(Collectors.toMap(ProductCategory::getCategoryType, ProductCategory::getCategoryName));
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<String> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
