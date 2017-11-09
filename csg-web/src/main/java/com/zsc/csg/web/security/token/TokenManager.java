package com.zsc.csg.web.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zsc.csg.core.redis.RedisClient;

/**
 * redis实现token管理
 * 
 */
@Component
public class TokenManager {

    private static final String TOKEN_NAME = "Csg-Token";

    //private static long EXPIRE_TIME = 20 * 60 * 1000L; // 20m XXX 写入配置文件中，使用的时候读取
    private static long EXPIRE_TIME = 20 * 60 * 10L; // 20m XXX 写入配置文件中，使用的时候读取

    @Autowired
    private RedisClient redisClient;

    /**
     * token放入redis
     * 
     * @param token
     */
    public void putToken(Token token) {
        redisClient.setCacheForHash(token.getTokenId(), TOKEN_NAME, token);
        redisClient.setExpireTime(token.getTokenId(), EXPIRE_TIME);
    }

    /**
     * 获取 token
     * 
     * @param tokenId
     * @return
     */
    public Token getToken(String tokenId) {
        return (Token) redisClient.getCacheForHash(tokenId, TOKEN_NAME);
    }

    /**
     * 移除 token
     * 
     * @param tokenId
     */
    public void removeToken(String tokenId) {
        redisClient.deleteCacheKey(tokenId);
    }

    /**
     * token是否存在
     * 
     * @param tokenId
     */
    public boolean containsToken(String tokenId) {
        return redisClient.existsCacheKey(tokenId) ? true : false;
    }

    /**
     * 刷新token
     * 
     * @param tokenId
     */
    public void refreshToken(String tokenId) {
        redisClient.setExpireTime(tokenId, EXPIRE_TIME);
    }
}
