package com.sky.repository;

import com.sky.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    List<ProductCategory> findByCategoryTypeIn(List<String> categoryTypeList);
}
