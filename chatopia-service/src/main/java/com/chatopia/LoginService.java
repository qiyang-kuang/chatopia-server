package com.chatopia;

import com.chatopia.dto.LoginDto;

public interface LoginService {
    /**
     * 登录
     *
     * @param loginDto 登录信息
     * @return token 字符串
     */
    String login(LoginDto loginDto);
}
