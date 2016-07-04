package com.fusw.mvc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc.util
 * @Date 16/6/19下午6:47
 * @Description 描述
 */
public final class CodecUtil {

    public static String encodeURL(String url){

        String target = null;
        try {
            target = URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("编码失败");
        }

        return target;
    }

    public static String decodeURL(String url){

        String target = null;
        try {
            target = URLDecoder.decode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("解码失败");
        }

        return target;
    }
}
