package com.jack.app.api.controller;

import com.jack.app.bean.consts.Const;
import com.jack.app.bean.exception.ExceptionStatus;
import com.jack.app.bean.response.BasicResponse;
import com.jack.app.bean.utils.MD5Util;
import com.jack.app.mysql.domain.User;
import com.jack.app.mysql.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description:用户控制器
 * @author: JackWu
 * @create: 2018-08-06 14:39
 **/
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "注册用户",notes = "注册用户")
    @ApiImplicitParam(name = "user",value = "用户注册信息",required = true,paramType = "User")
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public BasicResponse register(@RequestBody User user){
        User registerUser = userRepository.findByEmail(user.getEmail());
        if (null != registerUser){
            return new BasicResponse(ExceptionStatus.EMAIL_USED);
        }
        User userNameUser = userRepository.findByUserName(user.getUserName());
        if (null != userNameUser){
            return new BasicResponse(ExceptionStatus.USERNAME_USED);
        }
        user.setPassWord(MD5Util.encrypt(user.getPassWord() + Const.PASSWORD_KEY));
        user.setCreateTime(new Date());
        user.setLastModifyTime(new Date());
        user.setProfilePicture(Const.PROFILE＿PICTURE);
        userRepository.save(user);
        return BasicResponse.success();
    }

}
