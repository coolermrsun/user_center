package com.szk.user_center.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/*
Data数据标注注解，智能提供setter，getter方法
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;
    private String userAccount;
    private String userPassword;

}
