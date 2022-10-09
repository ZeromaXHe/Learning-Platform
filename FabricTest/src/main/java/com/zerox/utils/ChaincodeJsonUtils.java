package com.zerox.utils;

import com.google.gson.Gson;

import java.util.logging.Logger;

/**
 * @author zhuxi
 * @apiNote 链码中貌似用不了 Spring Boot 导入的依赖，暂时没理解原因，但是 pom 里面导入的依赖（比如这里的 gson）就可以正常使用
 * @implNote
 * @since 2022/9/29 10:32
 */
public class ChaincodeJsonUtils {
    private final static Logger LOG = Logger.getLogger(ChaincodeJsonUtils.class.getName());

    private static final Gson gson = new Gson();

    public static <T> T jsonToObject(String json, Class<T> objectClass) {
        try {
            return gson.fromJson(json, objectClass);
        } catch (Exception e) {
            LOG.severe("ChaincodeJsonUtils.jsonToObject failed|" + json + "|" + objectClass.getSimpleName());
            return null;
        }
    }

    public static String objectToJson(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            LOG.severe("ChaincodeJsonUtils.jsonToObject failed|" + object);
            return null;
        }
    }
}
