package com.fusw.mvc;

import java.lang.reflect.Field;
import java.util.Map;

import com.fusw.mvc.annotation.AutoImport;
import com.fusw.mvc.util.BeanInstantancsUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc
 * @Date 16/6/19下午5:11
 * @Description 描述
 */
public final class IocHelper {

	static {

		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

		if (MapUtils.isNotEmpty(beanMap)) {

			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {

				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();

				Field[] beanFields = beanClass.getDeclaredFields();

				if (ArrayUtils.isNotEmpty(beanFields)) {

					for (Field field : beanFields) {

                        if(field.isAnnotationPresent(AutoImport.class)){

                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);

                            if(beanFieldInstance != null){
                                BeanInstantancsUtil.setField(beanInstance,field,beanFieldInstance);
                            }
                        }
					}
				}
			}
		}
	}
}
