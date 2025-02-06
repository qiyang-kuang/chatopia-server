package com.chatopia.handler;

import com.chatopia.annotation.Auth;
import com.chatopia.dto.JoinRoomDto;
import com.chatopia.entity.User;
import com.chatopia.securtiy.AuthContent;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JoinRoomHandler implements Handler<JoinRoomDto> {
//    @Resource
//    private JoinRoomService joinRoomService;

    @Override
    @Auth
    public void handle(ChannelHandlerContext channelHandlerContext, JoinRoomDto joinRoomDto) {
        User loginUser = AuthContent.getLoginUser();
        try{
//            joinRoomService.joinRoom(loginUser, joinRoomDto);
        }catch (Exception e){
            channelHandlerContext.fireExceptionCaught(e);
        }
    }
}
