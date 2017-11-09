package com.zsc.csg.web.security;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zsc.csg.web.common.WebContext;
import com.zsc.csg.web.security.token.TokenManager;

/**
 * 校验Token信息
 * 
 */
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class); 
    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	// 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 若目标方法忽略了安全性检查，则直接调用目标方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
        	return true;
        }
        logger.info("--------------------token检查拦截器---------------------------");
        // 从切点上获取目标方法
        // 从 request header 中获取当前 token
        String tokenId = request.getHeader("Csg-Token");
        if(null == tokenId) {
        	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        	return false;
        }
        // 检查 token 有效性 如果验证token失败返回登录页面
        if (!tokenManager.containsToken(tokenId)) {
        	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response); 
        	//response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	return false;	
        }
        // 当前线程绑定token
        WebContext.initToken(tokenId);
        // 刷新 token
        tokenManager.refreshToken(tokenId);
        // 调用目标方法
        
        return true;
    }
}
