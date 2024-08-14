package com.myTemplateProject.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * 拦截器拦截路径配置（JWT为例）
 */
@Configuration
public class InterceptConfig implements WebMvcConfigurer {

//    String[] excludePathPatternsList={
//            "/api/user/login",
//            "/api/user/sign",
//            "/api/user/save"
//    };
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JwtInterceptor())
//                .addPathPatterns("/api/user/queryAllName")
//                .excludePathPatterns(Arrays.asList(excludePathPatternsList));
//    }
}
