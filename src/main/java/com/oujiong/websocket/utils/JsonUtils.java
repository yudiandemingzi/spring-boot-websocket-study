package com.oujiong.websocket.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xub
 * @since 2018年8月9日
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * ObjectMapper 序列化bytes
     */
    public static byte[] bytesFromObject(Object object) {
        try {
            if (Objects.isNull(object)) {
                return null;
            }
            return MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to serialize to json: " + object, e);
            return null;
        }
    }

    /**
     * ObjectMapper 反序列化json
     */
    public static <T> T objectFromJson(String json, Class<T> klass) {
        T object;
        try {//设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
            MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                    .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            object = MAPPER.readValue(json, klass);
        } catch (RuntimeException e) {
            LOGGER.error("Runtime exception during deserializing " + klass.getSimpleName() + " from " + StringUtils
                    .abbreviate(json, 80));
            throw e;
        } catch (Exception e) {
            LOGGER.error("Exception during deserializing " + klass.getSimpleName() + " from " + StringUtils
                    .abbreviate(json, 80));
            return null;
        }
        return object;
    }


}
