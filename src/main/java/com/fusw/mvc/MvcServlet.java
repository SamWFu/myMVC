package com.fusw.mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fusw.mvc.bean.Data;
import com.fusw.mvc.bean.Handler;
import com.fusw.mvc.bean.Param;
import com.fusw.mvc.util.BeanInstantancsUtil;
import com.fusw.mvc.util.JsonUtil;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc
 * @Date 16/6/19下午6:27
 * @Description 描述
 */

@WebServlet(urlPatterns = "/*" ,loadOnStartup = 0)
public class MvcServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {


		ServletContext servletContext = config.getServletContext();
		InitLoadingHelper.init();
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getJspUri() + "*");
		ServletRegistration assetServlet = servletContext.getServletRegistration("default");
		assetServlet.addMapping(ConfigHelper.getAssetUri());

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();

		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);

		if(handler !=null){

			Class<?> controllerClass = handler.getControllerClass();

			Object controllerBean = BeanHelper.getBean(controllerClass);

			Map<String,Object> paramMap = new HashMap<String, Object>();

			Enumeration<String> paramNames = request.getParameterNames();

			while (paramNames.hasMoreElements()){
				String name = paramNames.nextElement();

				String value = request.getParameter(name);

				paramMap.put(name, value);
			}

			Param param = new Param(paramMap);

			Method actionMethod = handler.getActionMethod();
			Object result = BeanInstantancsUtil.invokeMethod(controllerBean,actionMethod,param);

			Data data = (Data)result;

			Object model = data.getModel();
			if(model !=null){

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();

				String json = JsonUtil.toJson(model);
				writer.write(json);
				writer.flush();
				writer.close();
			}
		}

	}

}
