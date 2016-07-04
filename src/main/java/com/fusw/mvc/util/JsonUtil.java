package com.fusw.mvc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc.util
 * @Date 16/6/19下午6:59
 * @Description 描述
 */
public final class JsonUtil {

    private static final ObjectMapper OBJECT_MAP= new ObjectMapper();

    public static <T> String toJson(T obj){

        String json = "";
        try {
            json = OBJECT_MAP.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("JSON转化失败");
        }

        return json;
    }

}
