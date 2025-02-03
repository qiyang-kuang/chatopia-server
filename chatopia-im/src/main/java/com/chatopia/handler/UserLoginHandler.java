package com.chatopia.handler;

import com.chatopia.cache.RedisCache;
import com.chatopia.dto.LoginDto;
import com.chatopia.entity.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class UserLoginHandler implements Handler<LoginDto>{
    public static final String TOKEN = "token";

    @Resource
    private RedisCache redisCache;

    @Resource
    private IUserService userService;

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
        if (Objects.isNull(loginDto.getEmail()) || Objects.isNull(loginDto.getCaptcha())){
            // TODO 登陆信息为空异常
            channelHandlerContext.writeAndFlush(null);
            return;
        }
        String captcha = redisCache.getCacheObject(loginDto.getEmail());
        if (Objects.isNull(captcha)){
            // TODO 验证码为空异常
            channelHandlerContext.writeAndFlush(null);
            return;
        }
        if (!captcha.equals(loginDto.getCaptcha())){
            // TODO 验证码错误异常
            channelHandlerContext.writeAndFlush(null);
            return;
        }
        // 验证登陆
        User user = userService.selectByEmail(loginDto.getEmail());
        if (Objects.isNull(user)){
            // TODO 登陆失败异常
            channelHandlerContext.writeAndFlush(null);
        }

        // 登陆成功
        channelHandlerContext.channel().attr(AttributeKey.valueOf(USER_ID)).set(user.getUserId());
        channelHandlerContext.writeAndFlush(null);

        // TODO 存入缓存
    }
}
