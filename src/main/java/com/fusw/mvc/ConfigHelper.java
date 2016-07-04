package com.fusw.mvc;


import com.fusw.mvc.util.PropsUtil;

import java.util.Properties;

import static com.fusw.mvc.ConfigReader.CONFIG_FILE;


/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc
 * @Date 16/6/13上午6:12
 * @Description 描述
 */
public final class ConfigHelper {

    private static PropsUtil propsUtil = new PropsUtil(CONFIG_FILE);


    public static String getPackageUri (){

        return propsUtil.getString("mvc.framwork.package_uri");
    };

    public static String getJspUri(){

        return propsUtil.getString("mvc.framwork.jsp_uri");
    }

    public static String getAssetUri(){

        return propsUtil.getString("mvc.framwork.asset_uri");
    }
}
