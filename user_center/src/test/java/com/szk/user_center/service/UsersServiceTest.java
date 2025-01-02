package com.szk.user_center.service;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.szk.user_center.model.domain.Users;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/*
用户服务测试
@author szk
 */
@SpringBootTest
public class UsersServiceTest {

    @Resource
    private UsersService userService;

    @Test
    public void testAddUser() {
        Users user = new Users();
        user.setUsername("szk");
        user.setUserAccount("1234");
        user.setUserPassword("xxx");
        user.setGender(0);
        user.setAvatarUrl("https://cn.bing.com/images/search?q=%E5%9B%BE%E7%89%87&view=detailv2&FORM=IQFRBA&id=7CFFD1044B4096392CD8261CBC67D03F75B0017D&selectedindex=0&&expw=256&exph=256&ccid=jdP04yEo&ck=99EA2241C44D84CBA72C8B913DB18A7E&thid=OIP.jdP04yEoxG10mcywseQj7gAAAA&idpp=serp&idpbck=1");
        user.setPhone("123");
        user.setEmail("456");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() throws NoSuchAlgorithmException {
        String userAccount = "yupi123";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        userService.userRegister(userAccount,userPassword,checkPassword);
        //Assertions.assertEquals(-1,result);
        userAccount = "yu";
        long result = 0;
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount = "yupi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount = "yu pi";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);
        userPassword = "123456";
        userAccount = "szk";
        checkPassword = "123456";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

    }
}