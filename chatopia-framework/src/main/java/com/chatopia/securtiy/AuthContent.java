package com.chatopia.securtiy;

import com.chatopia.entity.User;

public class AuthContent extends ThreadLocal<User> {
    private static final ThreadLocal<User> authContent = new ThreadLocal<>();

    public static User getLoginUser() {
        return authContent.get();
    }

    public static void setLoginUser(User user) {
        authContent.set(user);
    }
}
