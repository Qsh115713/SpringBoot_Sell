package com.sky.controller;

import com.sky.dataobject.ProductCategory;
import com.sky.dataobject.ProductDetail;
import com.sky.enums.ResultStatusEnum;
import com.sky.service.CategoryService;
import com.sky.service.ProductService;
import com.sky.viewobject.ProductDetailVO;
import com.sky.viewobject.ProductVO;
import com.sky.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123")
    public ResultVO list() {
        //1、查询所有的上架商品
        List<ProductDetail> productDetailList = productService.findUpList();
        //2、查询类目（一次性查询）
        /*List<Integer> categoryTypeList = new ArrayList<>();
        //传统
        for (ProductDetail productInfo : productDetailList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //精简（Java8，lambda）
        List<String> categoryTypeList = productDetailList.stream()
                .map(ProductDetail::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3、查询封装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductDetailVO> productDetailVOList = new ArrayList<>();
            for (ProductDetail productDetail : productDetailList) {
                if (productDetail.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductDetailVO productDetailVO = new ProductDetailVO();
                    BeanUtils.copyProperties(productDetail, productDetailVO);
                    productDetailVOList.add(productDetailVO);
                }
            }
            productVO.setProductDetailVOList(productDetailVOList);
            productVOList.add(productVO);
        }

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultStatusEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultStatusEnum.SUCCESS.getMsg());
        resultVO.setData(productVOList);

        return resultVO;
    }
}
