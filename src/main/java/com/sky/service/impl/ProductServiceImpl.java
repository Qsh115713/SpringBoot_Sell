package com.sky.service.impl;

import com.sky.dataobject.ProductDetail;
import com.sky.dto.CartDTO;
import com.sky.enums.ProductStatusEnum;
import com.sky.enums.ResultEnum;
import com.sky.exception.SellException;
import com.sky.repository.ProductDetailRepository;
import com.sky.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDetailRepository repository;

    @Autowired
    public void setRepository(ProductDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDetail findByProductId(String productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductDetail> findUpList() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductDetail> findList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        return repository.save(productDetail);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductDetail productDetail = repository.findById(cartDTO.getProductId()).orElse(null);
            if (productDetail == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer remain = productDetail.getProductStock() + cartDTO.getProductQuantity();
            productDetail.setProductStock(remain);
            repository.save(productDetail);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductDetail productDetail = repository.findById(cartDTO.getProductId()).orElse(null);;
            if (productDetail == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer remain = productDetail.getProductStock() - cartDTO.getProductQuantity();
            if (remain < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productDetail.setProductStock(remain);
            repository.save(productDetail);
        }
    }
}
