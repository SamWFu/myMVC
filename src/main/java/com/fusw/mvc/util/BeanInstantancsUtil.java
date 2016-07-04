package com.fusw.mvc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc.util
 * @Date 16/6/19下午4:21
 * @Description 描述
 */
public final class BeanInstantancsUtil {

	public static Object newInstance(Class<?> cls) {

		Object instance = null;

		try {
			instance = cls.newInstance();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("实例化失败");
		}

		return instance;
	}

	public static Object invokeMethod(Object obj, Method method, Object... arg) {

		Object result = null;
		method.setAccessible(true);
		try {
			result = method.invoke(obj, arg);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("调用方法失败");
		}
		return result;
	}

    public  static void setField(Object object, Field field, Object value){

        field.setAccessible(true);
        try {
            field.set(object,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("注入失败");
        }
    }
}
