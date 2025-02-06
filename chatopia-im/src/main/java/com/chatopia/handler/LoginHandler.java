package com.chatopia.handler;

import com.chatopia.LoginService;
import com.chatopia.cache.RedisCache;
import com.chatopia.dto.LoginDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class LoginHandler implements Handler<LoginDto>{
    public static final String TOKEN = "token";

    @Resource
    private RedisCache redisCache;
    @Resource
    private LoginService loginService;

    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, LoginDto loginDto) {
        if (Objects.isNull(channelHandlerContext) || Objects.isNull(loginDto)) return;
        // 判断是否已经登陆
        String token = (String) channelHandlerContext.channel().attr(AttributeKey.valueOf(TOKEN)).get();
        if (!Objects.isNull(token)){
            // TODO 已登录信息
            channelHandlerContext.writeAndFlush(null);
            return;
        }
        // 登陆逻辑
        try{
            token = loginService.login(loginDto);
            // 登陆成功
            channelHandlerContext.channel().attr(AttributeKey.valueOf(TOKEN)).set(token);
            channelHandlerContext.writeAndFlush(null);
        }catch (Exception e){
            // TODO 登陆失败异常
            channelHandlerContext.writeAndFlush(null);
            return;
        }


        // TODO 存入缓存
    }
}
