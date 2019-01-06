package com.mwl.user.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author mawenlong
 * @date 2019/01/06
 */
@Data
@Entity
public class UserInfo {
    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;
}
