package com.mwl.product.exception;

import com.mwl.product.enums.ResultEnum;

/**
 * @author mawenlong
 * @date 2018/12/19
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
