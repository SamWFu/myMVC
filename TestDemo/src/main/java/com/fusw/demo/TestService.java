package com.fusw.demo;

import java.util.HashMap;
import java.util.Map;

import com.fusw.mvc.annotation.Service;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.demo
 * @Date 16/6/19下午7:06
 * @Description 描述
 */

@Service
public class TestService {

	public Map<String, String> test() {

		Map map = new HashMap();

		map.put("success", "success Info");
		return map;
	}
}
