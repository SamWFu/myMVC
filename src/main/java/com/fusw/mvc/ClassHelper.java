package com.fusw.mvc;

import com.fusw.mvc.annotation.Controller;
import com.fusw.mvc.annotation.Service;
import com.fusw.mvc.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc
 * @Date 16/6/19下午4:08
 * @Description 描述
 */
public final class ClassHelper {

    private static final Set<Class<?>>  CLASS_SET;

    static {

        String basePackage = ConfigHelper.getPackageUri();

        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    public static Set<Class<?>> getServiceClassSet(){

        Set<Class<?>>  classSet= new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){

            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
   }


    public static Set<Class<?>> getControllerClassSet(){

        Set<Class<?>>  classSet= new HashSet<Class<?>>();

         for(Class<?> cls : CLASS_SET){

            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }


    //获取所有的bean(以防有未注解的bean)
    public static Set<Class<?>> getBeanClassSet(){

        Set<Class<?>>  beanClassSet= new HashSet<Class<?>>();

        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());

        return beanClassSet;
    }
}
