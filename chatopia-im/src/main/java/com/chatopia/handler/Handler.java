package com.chatopia.handler;


import io.netty.channel.ChannelHandlerContext;

/**
 * @author : kuangqiyang
 * @date: 2025/1/31
 * @description: handler接口
 */
public interface Handler<MESSAGE> {
    /**
     * 处理消息
     *
     * @param channelHandlerContext 通道
     * @param message               消息
     */
    void handle(ChannelHandlerContext channelHandlerContext, MESSAGE message);
}
