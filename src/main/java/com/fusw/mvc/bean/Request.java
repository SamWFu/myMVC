package com.fusw.mvc.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc.bean
 * @Date 16/6/19下午5:53
 * @Description 描述
 */
public class Request {

	private String requestMethod;

	private String requestPath;

	public Request(String requestMethod, String requestPath) {
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}

	public String getRequestMethod() {

		return requestMethod;
	}

	public String getRequestPath() {

		return requestPath;
	}

	@Override
	public boolean equals(Object obj) {

		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {

		return HashCodeBuilder.reflectionHashCode(this);
	}
}
