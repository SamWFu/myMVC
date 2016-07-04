package com.fusw.mvc;

import com.fusw.mvc.util.ClassUtil;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc
 * @Date 16/6/19下午6:16
 * @Description 描述
 */
public final class InitLoadingHelper {

    public static void init(){

        Class<?>[]  classList={

                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ConfigHelper.class,
                ControllerHelper.class
        };

        for(Class<?> cls:classList){

            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}
