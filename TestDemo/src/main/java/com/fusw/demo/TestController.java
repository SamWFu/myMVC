package com.fusw.demo;

import com.fusw.mvc.annotation.Action;
import com.fusw.mvc.annotation.AutoImport;
import com.fusw.mvc.annotation.Controller;
import com.fusw.mvc.bean.Data;
import com.fusw.mvc.bean.Param;

import java.util.Map;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.demo
 * @Date 16/6/19下午7:06
 * @Description 描述
 */

@Controller
public class TestController {

    @AutoImport
    private TestService testService;

    @Action("get:/test")
    public Data  testAction(Param param){

        Map map =   testService.test();
        return new Data(map);
    };
    @Action("post:/test")
    public Data  testPostAction(Param param){

        Map map =   testService.test();
        return new Data(map);
    };
}
