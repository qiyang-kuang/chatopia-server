package com.chatopia.securtiy;

import com.chatopia.cache.RedisCache;
import com.chatopia.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class TokenService {
    @Resource
    private RedisCache redisCache;

    private String secret = "chatopia";

    private String claimsKey = "chatopia:login:user:key";

    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public User getLoginUser(String token) {
        if (token == null || token.isEmpty()) return null;
        try {
            Claims claims = parseToken(token);
            if (claims == null) return null;
            String uuid = claims.get(claimsKey).toString();
            return redisCache.getCacheObject(claimsKey + uuid);
        } catch (Exception e) {
            log.error("获得用户信息异常，{}", e.getMessage());
            return null;
        }
    }

    public String createToken(User user) {
        return "";
    }
}
