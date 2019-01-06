package com.mwl.user.service;

import com.mwl.user.bean.UserInfo;

public interface UserService {

    UserInfo findByOpenid(String openid);
}
