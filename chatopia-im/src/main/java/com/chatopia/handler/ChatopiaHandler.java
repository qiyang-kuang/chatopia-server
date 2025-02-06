package com.chatopia.handler;

import com.chatopia.cache.RedisCache;
import com.chatopia.global.GlobalContainer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ChatopiaHandler extends SimpleChannelInboundHandler<Object> {

    @Resource
    private GlobalContainer globalContainer;

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        globalContainer.removeLoginUserContext(ctx);
    }

    /**
     * 接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
