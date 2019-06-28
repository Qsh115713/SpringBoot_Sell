package com.sky.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryForm {

    private String categoryId;

    //类目名字
    @NotEmpty
    private String categoryName;

    //类目编号
    @NotEmpty
    private String categoryType;
}
