package com.wut.tool;  

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;  
import org.glassfish.jersey.server.ResourceConfig;  

/**
 * 应用
 * @author waylau.com
 * 2014-3-18
 */  
public class RestApplication extends ResourceConfig {  
    public RestApplication() {  

     //服务类所在的包路径
     packages("com.wut.tool.resources");  
     //注册JSON转换器
     register(JacksonJsonProvider.class);  

    }  
}