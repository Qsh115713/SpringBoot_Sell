package com.sky.service;

import com.sky.dataobject.ProductDetail;
import com.sky.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductDetail findByProductId(String productId);

    //查询在架的商品
    List<ProductDetail> findUpList();

    Page<ProductDetail> findList(Pageable pageable);

    ProductDetail save(ProductDetail productDetail);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductDetail onSale(String productId);

    //下架
    ProductDetail offSale(String productId);
}
