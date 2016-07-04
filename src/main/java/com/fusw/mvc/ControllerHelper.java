package com.fusw.mvc;

import com.fusw.mvc.annotation.Action;
import com.fusw.mvc.bean.Handler;
import com.fusw.mvc.bean.Request;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc
 * @Date 16/6/19下午5:52
 * @Description 描述
 */
public final class ControllerHelper {

private  static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {

        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();

        if(CollectionUtils.isNotEmpty(controllerClassSet)){

            for (Class<?> controllerClass :controllerClassSet){

                Method[] methods = controllerClass.getMethods();
                if(ArrayUtils.isNotEmpty(methods)){

                    for(Method method:methods){

                        if(method.isAnnotationPresent(Action.class)){

                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();

                            if(mapping.matches("\\w+:/\\w*"));

                            String[] mappingValue = mapping.split(":");

                            if(mappingValue.length == 2){

                                String requestMethod = mappingValue[0];
                                String requestPath = mappingValue[1];

                                Request request = new Request(requestMethod,requestPath);

                                Handler handler = new Handler(method,controllerClass);

                                ACTION_MAP.put(request,handler);
                            }

                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod,String requestPath){

        Request request = new Request(requestMethod,requestPath);

        return ACTION_MAP.get(request);
    }
}
