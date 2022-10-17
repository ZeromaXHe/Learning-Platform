package com.zerox.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
            logger.error("JsonUtils.jsonToObject failed|{}|{}", json, objectClass.getSimpleName(), e);
            return null;
        }
    }

    public static <T> List<T> jsonToList(String json, Class<T> elementClass) {
        try {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, elementClass);
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            logger.error("JsonUtils.jsonToList failed|{}|{}", json, elementClass.getSimpleName(), e);
            return null;
        }
    }

    public static String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("JsonUtils.jsonToObject failed|{}", object, e);
            return null;
        }
    }
}
