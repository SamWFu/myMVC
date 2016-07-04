package com.fusw.mvc.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc.util
 * @Date 16/6/13上午7:26
 * @Description 描述
 */
public class PropsUtil {


    private Properties props = null;

    public PropsUtil(String propsPath) {
        this(propsPath, "UTF-8");
    }

    public PropsUtil(String propsPath, String encoding) {
        InputStream is = null;
        try {
            if (StringUtils.isBlank(propsPath)) {
                throw new IllegalArgumentException();
            }
            String suffix = ".properties";
            if (propsPath.lastIndexOf(suffix) == -1) {
                propsPath += suffix;
            }
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsPath);
            if (is != null) {
                props = new Properties();
                props.load(new InputStreamReader(is, encoding));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {

            }
        }
    }

    /**
     * 加载属性文件，并转为 Map
     */
    public Map<String, String> loadPropsToMap(String propsPath) {
        Map<String, String> map = new HashMap<String, String>();
        for (String key : props.stringPropertyNames()) {
            map.put(key, props.getProperty(key));
        }
        return map;
    }

    /**
     * 获取字符型属性
     */
    public String getString(String key) {
        return props.getProperty(key);
    }

    /**
     * 获取字符型属性，有默认值
     */
    public String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    /**
     * 获取字符型属性（有默认值）
     */
    public static String getString(Properties props, String key, String defalutValue) {
        String value = defalutValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值型属性
     */
    public Integer getInt(String key) {
        return getInt(key, null);
    }
    public Integer getInt(String key, Integer defaultValue) {
        String value = props.getProperty(key);
        if (value != null)
            return Integer.parseInt(value.trim());
        return defaultValue;
    }

    /**
     * 获取Long型
     */
    public Long getLong(String key) {
        return getLong(key, null);
    }
    public Long getLong(String key, Long defaultValue) {
        String value = props.getProperty(key);
        if (value != null)
            return Long.parseLong(value.trim());
        return defaultValue;
    }

    /**
     * 获取布尔型属性
     */
    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }
    public Boolean getBoolean(String key, Boolean defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            value = value.toLowerCase().trim();
            if ("true".equals(value))
                return true;
            else if ("false".equals(value))
                return false;
            throw new RuntimeException("The value can not parse to Boolean : " + value);
        }
        return defaultValue;
    }

    public boolean containsKey(String key) {
        return props.containsKey(key);
    }

    public Properties getProperties() {
        return props;
    }

}
