package com.zsc.csg.web.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * web上下文
 * 
 */
public class WebContext {

    // request hodler
    private static ThreadLocal<HttpServletRequest> requestHodler = new ThreadLocal<HttpServletRequest>();
    // response hodler
    private static ThreadLocal<HttpServletResponse> responseHodler = new ThreadLocal<HttpServletResponse>();
    // tokenId hodler
    private static ThreadLocal<String> tokenHodler = new ThreadLocal<String>();

    /**
     * 当前线程绑定request response对象
     * 
     * @param request
     * @param response
     */
    public static void init(HttpServletRequest request, HttpServletResponse response) {
        requestHodler.set(request);
        responseHodler.set(response);
    }

    /**
     * 当前线程绑定token
     * 
     * @param tokenId
     */
    public static void initToken(String tokenId) {
        tokenHodler.set(tokenId);
    }

    public static String getTokenId() {
        return tokenHodler.get();
    }
    
    public static HttpServletRequest getHttpServletRequest() {
        return requestHodler.get();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return responseHodler.get();
    }

    /**
     * 销毁当前线程绑定的资源
     */
    public static void destory() {
        requestHodler.remove();
        responseHodler.remove();
        tokenHodler.remove();
    }
}
