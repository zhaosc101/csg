package com.zsc.csg.core.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: redis 工具类
 */
@Component
public class RedisClient {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * dataType:string 设置缓存
     * 
     * @param key
     * @param value
     */
    public void setCacheForValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * dataType:string 获取缓存值
     * 
     * @param key
     * @return
     */
    public String getCacheForValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * dataType:hash 设置hash缓存值
     * 
     * @param key
     * @param hashKey
     * @param value
     */
    public void setCacheForHash(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * dataType:hash 获取hash缓存值
     * 
     * @param key
     * @param hashKey
     * @return
     */
    public Object getCacheForHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * dataType:string 设置缓存值并设置有效期
     * 
     * @param key
     * @param value
     */
    public void setCacheValueForTime(String key, String value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * dataType:hash 移除hashkey
     * 
     * @param key
     * @param hashKey
     * @return
     */
    public void deleteCacheKeyForHash(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * dataType:all 删除key值
     * 
     * @param key
     */
    public void deleteCacheKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * dataType:all 判断是否存在key
     * 
     * @param key
     * @return
     */
    public boolean existsCacheKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * dataType:all 设置缓存时间
     * 
     * @param key
     * @param timeout
     * @param unit
     */
    public void setExpireTime(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * dataType:all 获取缓存的有效期
     * 
     * @param key
     */
    public long getExpireTime(String key) {
        long time = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return time;
    }
}
