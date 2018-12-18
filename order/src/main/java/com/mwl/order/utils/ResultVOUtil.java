package com.mwl.order.utils;

import com.mwl.order.VO.ResultVO;

/**
 * @author mawenlong
 * @date 2018/12/18
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO failed(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("失败");
        resultVO.setData(object);
        return resultVO;
    }
}
