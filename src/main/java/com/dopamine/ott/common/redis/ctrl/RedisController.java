package com.dopamine.ott.common.redis.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController{


    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 데이터 조회 (GET)
    @GetMapping("/{key}")
    public String read(@PathVariable String key) {
        if (!redisTemplate.hasKey(key)) {
            return "No data found for key '" + key + "'";
        }
        String value = redisTemplate.opsForValue().get(key);
        return "Value for key '" + key + "': " + value;
    }

    // 데이터 생성 (POST)
    @PostMapping("/{key}/{value}")
    public String create(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value);
        return "Data saved successfully with key: " + key;
    }

    // 데이터 업데이트 (PUT)
    @PutMapping("/{key}/{value}")
    public String update(@PathVariable String key, @PathVariable String value) {
        if (!redisTemplate.hasKey(key)) {
            return "No data found for key '" + key + "'";
        }
        redisTemplate.opsForValue().set(key, value);
        return "Data updated successfully with key: " + key;
    }

    // 데이터 삭제 (DELETE)
    @DeleteMapping("/{key}")
    public String delete(@PathVariable String key) {
        if (!redisTemplate.hasKey(key)) {
            return "No data found for key '" + key + "'";
        }
        redisTemplate.delete(key);
        return "Data deleted successfully for key: " + key;
    }

}