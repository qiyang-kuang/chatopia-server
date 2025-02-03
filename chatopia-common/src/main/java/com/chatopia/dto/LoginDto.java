package com.chatopia.dto;

import lombok.Data;

@Data
public class LoginDto {
    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String captcha;
}
