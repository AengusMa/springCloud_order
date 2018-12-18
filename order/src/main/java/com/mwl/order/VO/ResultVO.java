package com.mwl.order.VO;

import lombok.Data;

/**
 * @author mawenlong
 * @date 2018/12/18
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
