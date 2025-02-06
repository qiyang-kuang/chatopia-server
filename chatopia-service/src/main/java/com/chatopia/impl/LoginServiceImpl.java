package com.chatopia.impl;

import com.chatopia.LoginService;
import com.chatopia.cache.RedisCache;
import com.chatopia.dto.LoginDto;
import com.chatopia.entity.User;
import com.chatopia.securtiy.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisCache redisCache;

//    @Resource
//    private CatchaService catchaService;
//
//    @Resource
//    private UserService userService;

    @Resource
    private TokenService tokenService;

    @Override
    public String login(LoginDto loginDto) {
        // 验证登录信息
        Objects.requireNonNull(loginDto, "登录信息不能为空");
        Objects.requireNonNull(loginDto.getEmail(), "邮箱不能为空");
        Objects.requireNonNull(loginDto.getCaptcha(), "验证码不能为空");

        // 验证验证码
//        catchaService.validateCaptcha(loginDto.getEmail(), loginDto.getCaptcha());

        // 验证用户信息
//        User user = userService.selectByEmail(loginDto.getEmail());
        User user = new User();
        user.setUserId(1L);
        user.setEmail("2248869923@qq.com");
        if (Objects.isNull(user)){
            // TODO 登陆失败异常
            return null;
        }

        // 验证成功，生成token
        return tokenService.createToken(user);
    }
}
