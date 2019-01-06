package com.mwl.user.repository;

import com.mwl.user.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
    UserInfo findByOpenid(String openid);

}
