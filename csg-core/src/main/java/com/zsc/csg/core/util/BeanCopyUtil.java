package com.zsc.csg.core.util;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cglib.beans.BeanCopier;

/**
 * @description: BeanCopyUtil.java
 * @author Tieli.Ma
 */
public class BeanCopyUtil {

    static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<String, BeanCopier>();

    public static void copy(Object srcObj, Object destObj) {
        String key = genKey(srcObj.getClass(), destObj.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(srcObj, destObj, null);
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + "#" + destClazz.getName();
    }

}
