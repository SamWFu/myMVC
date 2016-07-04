package com.fusw.mvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fusw.mvc.util.BeanInstantancsUtil;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc
 * @Date 16/6/19下午4:27
 * @Description 描述
 */
public final class BeanHelper {

	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

	static {

		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();

		for (Class<?> beanClass : beanClassSet) {

			Object obj = BeanInstantancsUtil.newInstance(beanClass);

			BEAN_MAP.put(beanClass, obj);
		}
	}

	public static Map<Class<?>, Object> getBeanMap() {

		return BEAN_MAP;
	}

	public static <T> T getBean(Class<T> cls) {

		if (!BEAN_MAP.containsKey(cls))
			throw new RuntimeException("不能获取对应的bean");

		return (T) BEAN_MAP.get(cls);
	}
}
