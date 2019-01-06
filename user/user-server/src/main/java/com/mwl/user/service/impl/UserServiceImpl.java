package com.mwl.user.service.impl;

import com.mwl.user.bean.UserInfo;
import com.mwl.user.repository.UserInfoRepository;
import com.mwl.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mawenlong
 * @date 2019/01/06
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
