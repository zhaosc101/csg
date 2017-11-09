package com.zsc.csg.core.util;
/**
 * 多数据元切换
 * @author zsc
 *
 */
public class DataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
	  
    public static void setDbType(String dbType) {  
           contextHolder.set(dbType);  
    }  
 
    public static String getDbType() {  
           return ((String) contextHolder.get());  
    }  
 
    public static void clearDbType() {  
           contextHolder.remove();  
    }  
}
