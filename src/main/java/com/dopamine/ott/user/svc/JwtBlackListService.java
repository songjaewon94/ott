package com.dopamine.ott.user.svc;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class JwtBlackListService {
    private final RedisTemplate<String, String> redisTemplate;

    public JwtBlackListService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addToBlacklist(String token) {
        redisTemplate.opsForValue().set(token, "blacklisted");
    }

    public boolean isBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }
}
