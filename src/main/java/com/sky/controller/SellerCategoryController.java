package com.sky.controller;

import com.sky.dataobject.ProductCategory;
import com.sky.enums.ResultEnum;
import com.sky.exception.SellException;
import com.sky.form.CategoryForm;
import com.sky.service.CategoryService;
import com.sky.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findList();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) String categoryId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(categoryId)) {
            ProductCategory category = categoryService.findByCategoryId(categoryId);
            map.put("category", category);
        }
        return new ModelAndView("category/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        try {
            ProductCategory category = new ProductCategory();
            //Id不为空，修改
            if (!StringUtils.isEmpty(categoryForm.getCategoryId())) {
                category = categoryService.findByCategoryId(categoryForm.getCategoryId());
            } else {
                categoryForm.setCategoryId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(categoryForm, category);
            categoryService.save(category);
            map.put("msg", ResultEnum.CATEGORY_SAVE_SUCCESS.getMsg());
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
