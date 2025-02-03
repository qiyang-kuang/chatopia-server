package com.chatopia.aop;
import com.chatopia.annotation.Auth;
import com.chatopia.entity.User;
import com.chatopia.securtiy.TokenService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.annotation.Resource;
import java.util.Objects;

@Aspect
public class HandlerAop {
    @Resource
    private TokenService tokenService;

    private static final String TOKEN = "token";
    @Before(value = "@annotation(auth)")
    public void before(JoinPoint joinPoint, Auth auth) {
        Object[] args = joinPoint.getArgs();
        if (args.length != 2) throw new RuntimeException("参数错误");
        Object arg = args[0];
        if (!(arg instanceof ChannelHandlerContext)) throw new RuntimeException("参数错误");
        String token = (String)((ChannelHandlerContext) arg).channel().attr(AttributeKey.valueOf(TOKEN)).get();
        User user = tokenService.getLoginUser(token);
        if (Objects.isNull(user)){
            // TODO 未登录异常
            throw new RuntimeException("未登录");
        }
    }

}
