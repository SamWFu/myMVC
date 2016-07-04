package com.fusw.mvc.bean;

import java.util.Map;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc.bean
 * @Date 16/6/19下午6:21
 * @Description 描述
 */
public class Param {

    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Object getParamByKey (String key){

        return paramMap.get(key);
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
