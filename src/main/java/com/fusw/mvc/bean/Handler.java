package com.fusw.mvc.bean;

import java.lang.reflect.Method;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc.bean
 * @Date 16/6/19下午5:57
 * @Description 描述
 */
public class Handler {

	private Class<?> controllerClass;

	private Method actionMethod;

	public Handler(Method actionMethod, Class<?> controllerClass) {
		this.actionMethod = actionMethod;
		this.controllerClass = controllerClass;
	}

	public Method getActionMethod() {

		return actionMethod;
	}

	public Class<?> getControllerClass() {

		return controllerClass;
	}
}
