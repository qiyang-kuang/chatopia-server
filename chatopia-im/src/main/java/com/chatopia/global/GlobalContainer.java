package com.chatopia.global;

import com.chatopia.cache.RedisCache;
import com.chatopia.exception.ServiceException;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * @author kuangqiyang
 * 全局容器
 */
@Component
public class GlobalContainer {
    @Resource
    private RedisCache redisCache;

    @PostConstruct
    public void init() {
        // 初始化登录用户集合
        Set<ChannelHandlerContext> loginUserContextSet = redisCache.getCacheSet("login:user:context");
        if (Objects.isNull(loginUserContextSet)){
            loginUserContextSet = new HashSet<>();
            redisCache.setCacheSet("login:user:context", loginUserContextSet);
        }
    }

    public void addLoginUserContext(ChannelHandlerContext channelHandlerContext) {
        redisCache.addCacheSetValue("login:user:context", channelHandlerContext);
    }

    public void removeLoginUserContext(ChannelHandlerContext channelHandlerContext) {
        redisCache.removeCacheSetValue("login:user:context", channelHandlerContext);
    }

    public void addRoomUserContext(String roomId, ChannelHandlerContext channelHandlerContext) {
        Set<ChannelHandlerContext> roomUserContexts = redisCache.getCacheSet("room:users:context:" + roomId);
        if (Objects.isNull(roomUserContexts)){
            roomUserContexts = new HashSet<>();
            redisCache.setCacheSet("room:users:context:" + roomId, roomUserContexts);
        }
        redisCache.addCacheSetValue("room:users:context:" + roomId, channelHandlerContext);
    }

    public void removeRoomUserContext(String roomId, ChannelHandlerContext channelHandlerContext) {
        Set<ChannelHandlerContext> roomUserContexts = redisCache.getCacheSet("room:users:context:" + roomId);
        if (Objects.isNull(roomUserContexts)) throw new ServiceException("roomUserContexts is null");
        redisCache.removeCacheSetValue(roomId,channelHandlerContext);
    }
}
