package com.mwl.user.controller;

import com.mwl.user.VO.ResultVO;
import com.mwl.user.bean.UserInfo;
import com.mwl.user.constant.CookieConstant;
import com.mwl.user.enums.ResultEnum;
import com.mwl.user.enums.RoleEnum;
import com.mwl.user.service.UserService;
import com.mwl.user.utils.CookieUtil;
import com.mwl.user.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author mawenlong
 * @date 2019/01/06
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo==null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        if(RoleEnum.BUYER.getCode()!=userInfo.getRole()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        CookieUtil.set(response, CookieConstant.OPENID,openid,CookieConstant.expire);
        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletResponse response) {
        return null;
    }
}
