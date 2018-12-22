package com.mwl.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author mawenlong
 * @date 2018/12/18
 */
@Data
public class ItemForm {
    /**
     * 商品id
     */
    @NotEmpty(message = "商品id必填")
    private String productId;
    /**
     * 商品数量
     */
    @NotEmpty(message = "商品数量必填")
    private Integer productQuantity;
}
