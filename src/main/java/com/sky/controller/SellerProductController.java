package com.sky.controller;

import com.sky.dataobject.ProductDetail;
import com.sky.enums.ResultEnum;
import com.sky.exception.SellException;
import com.sky.form.ProductForm;
import com.sky.service.CategoryService;
import com.sky.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

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
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductDetail> productDetailPage = productService.findList(request);
        Map<String, String> categoryMap = categoryService.findCategoryMap();
        map.put("categoryMap", categoryMap);
        map.put("productDetailPage", productDetailPage);
        map.put("page", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        map.put("url", "/sell/seller/product/list");
        try {
            productService.onSale(productId);
            map.put("msg", ResultEnum.PRODUCT_UP_SALE_SUCCESS.getMsg());
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        map.put("url", "/sell/seller/product/list");
        try {
            productService.offSale(productId);
            map.put("msg", ResultEnum.PRODUCT_OFF_SALE_SUCCESS.getMsg());
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductDetail productDetail = productService.findByProductId(productId);
            map.put("productDetail", productDetail);
        }
        Map<String, String> categoryMap = categoryService.findCategoryMap();
        map.put("categoryMap", categoryMap);
        return new ModelAndView("product/index", map);
    }

    //保存或更新
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        ProductDetail productDetail = new ProductDetail();
        BeanUtils.copyProperties(productForm, productDetail);

        try {
            productService.save(productDetail);
            map.put("msg", ResultEnum.PRODUCT_INDEX_SUCCESS.getMsg());
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
