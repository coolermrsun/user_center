package com.szk.user_center.service;

import com.szk.user_center.model.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
* @author 孙枝坤
* @description 针对表【users】的数据库操作Service
* @createDate 2024-12-19 13:01:00
*/
public interface UsersService extends IService<Users> {

    /**
     * 用户注释
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户ID
     */
    long userRegister(String userAccount, String userPassword,String checkPassword) throws NoSuchAlgorithmException;

    /**
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     * @throws NoSuchAlgorithmException
     */
    Users UserLogin(String userAccount, String userPassword, HttpServletRequest request) throws NoSuchAlgorithmException;

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    Users getSafetyUser(Users originUser);

    int Logout(HttpServletRequest request);
}
