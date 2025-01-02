package com.szk.user_center.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szk.user_center.common.BaseResponse;
import com.szk.user_center.common.ErrorCode;
import com.szk.user_center.common.ResultUtils;
import com.szk.user_center.exception.BusinessException;
import com.szk.user_center.model.domain.Users;
import com.szk.user_center.model.domain.request.UserLoginRequest;
import com.szk.user_center.model.domain.request.UserRegisterRequest;
import com.szk.user_center.service.UsersService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import static com.szk.user_center.constant.constant.ADMIN_ROLE;
import static com.szk.user_center.constant.constant.USER_LOGIN_STATE;

/*
控制层，用来封装请求，
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UsersService usersService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) throws NoSuchAlgorithmException {
        if(userRegisterRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword() ;
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword))
        {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        long result = usersService.userRegister(userAccount,userPassword,checkPassword);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<Users> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request, BindingResult bindingResult) throws NoSuchAlgorithmException {
        if(userLoginRequest == null)
        {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword))
        {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        Users users = usersService.UserLogin(userAccount,userPassword,request);
        if (users==null)
        {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        //users = usersService.getSafetyUser(users);
        return ResultUtils.success(users);
    }

    @GetMapping("/current")
    public BaseResponse<Users> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        Users currentUser = (Users)userObj;
        if(currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        //校验用户是否合法
        Users user = usersService.getById(userId);
        Users safetyUser = usersService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }
    @GetMapping("/search")
    public BaseResponse<List<Users>> searchUsers(@RequestParam(required = false) String username,
                                                 @RequestParam(required = false) String userAccount,
                                                 @RequestParam(required = false) String phone,
                                                 HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 创建查询包装器
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();

        // 如果提供了用户名，则添加到查询条件中
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }

        // 如果提供了用户账户，则添加到查询条件中
        if (StringUtils.isNotBlank(userAccount)) {
            queryWrapper.like("userAccount", userAccount);
        }

        // 如果提供了电话号码，则添加到查询条件中
        if (StringUtils.isNotBlank(phone)) {
            queryWrapper.like("phone", phone);
        }

        // 检查是否至少有一个查询条件
        boolean hasQueryParams = StringUtils.isNotBlank(username) ||
                StringUtils.isNotBlank(userAccount) ||
                StringUtils.isNotBlank(phone);

        List<Users> userList;
        if (hasQueryParams) {
            // 如果有查询条件，执行带条件的查询
            userList = usersService.list(queryWrapper);
            List<Users> list = userList.stream().map(users -> usersService.getSafetyUser(users)).collect(Collectors.toList());
            return ResultUtils.success(list);
        } else {
            // 如果没有查询条件，返回所有数据
            userList = usersService.list();
            List<Users> list = userList.stream().map(users -> usersService.getSafetyUser(users)).collect(Collectors.toList());
            return ResultUtils.success(list);
        }
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUsers(@RequestBody long id,HttpServletRequest request){
        if(!isAdmin(request))
        {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        if(id <= 0)
        {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        boolean result = usersService.removeById(id);
        return ResultUtils.success(result);
    }
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if (request == null)
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        Integer result = usersService.Logout(request);
        return ResultUtils.success(result);
    }

    /**
     *  //是否为管理员
     * @param request 用户请求
     * @return
     */
    private boolean isAdmin(HttpServletRequest request){
        //仅管理员可以查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        Users user = (Users) userObj;
         return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
