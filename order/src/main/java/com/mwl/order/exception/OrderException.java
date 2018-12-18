package com.mwl.order.exception;

import com.mwl.order.enums.ResultEnum;

/**
 * @author mawenlong
 * @date 2018/12/18
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
