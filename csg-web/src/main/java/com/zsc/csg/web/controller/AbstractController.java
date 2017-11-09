package com.zsc.csg.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.suixingpay.common.web.controller.GenericController;
import com.zsc.csg.core.redis.RedisClient;
import com.zsc.csg.web.common.WebContext;
/**
 * Controller基类，提供获取国际化，邮件发送等相关服务。
 * 
 */
public class AbstractController extends GenericController {
	
	public final static int PAGENUM = 1;

	public final static int PAGESIZE = 10;
	@Autowired
	private RedisClient redisClient;
	  /**
     * 获取缓存中的属性
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
	private String getAttribute(String attribute) {
        String tokenId = WebContext.getTokenId();
		Map<String, String> map = (Map<String, String>) redisClient.getCacheForHash(tokenId, "USERINFO");
		if(null == map) {
			return null;
		}
        return map.get(attribute);
    }
    public String getUuid() {
        return getAttribute("UUID");
    }
}
