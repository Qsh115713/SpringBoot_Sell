<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productDetailPage.content as productDetail>
                            <tr>
                                <td>${productDetail.productId}</td>
                                <td>${productDetail.productName}</td>
                                <td><img height="100" width="100" src="${productDetail.productIcon}" alt=""></td>
                                <td>${productDetail.productPrice}</td>
                                <td>${productDetail.productStock}</td>
                                <td>${productDetail.productDescription}</td>
                                <td>${categoryMap[productDetail.categoryType]}</td>
                                <td>${productDetail.createTime}</td>
                                <td>${productDetail.updateTime}</td>
                                <td><a href="/sell/seller/product/index?productId=${productDetail.productId}">修改</a>
                                </td>
                                <td>
                                    <#if productDetail.getProductStatusMsg() == "在架">
                                        <a href="/sell/seller/product/off_sale?productId=${productDetail.productId}">下架</a>
                                    <#else>
                                        <a href="/sell/seller/product/on_sale?productId=${productDetail.productId}">上架</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if page lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${page - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..productDetailPage.getTotalPages() as index>
                            <#if page == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if page gte productDetailPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${page + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>