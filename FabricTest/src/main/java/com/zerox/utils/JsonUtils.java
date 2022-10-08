package com.zerox.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 10:32
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    // Jackson 的 ObjectMapper 在配置不修改的情况下是线程安全的，详情请在源码中搜索 thread-safe
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T jsonToObject(String json, Class<T> objectClass) {
        try {
            return mapper.readValue(json, objectClass);
        } catch (JsonProcessingException e) {
            logger.error("JsonUtils.jsonToObject failed|{}|{}", json, objectClass.getSimpleName());
            return null;
        }
    }

    public static String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("JsonUtils.jsonToObject failed|{}", object);
            return null;
        }
    }
}
