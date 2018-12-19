package com.mwl.product.service;

import com.mwl.product.bean.ProductInfo;
import com.mwl.product.common.DecreaseStockInput;
import com.mwl.product.common.ProductInfoOutput;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
