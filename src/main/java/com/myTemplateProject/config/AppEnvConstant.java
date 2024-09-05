package com.myTemplateProject.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@Slf4j
@ConfigurationProperties(prefix = "app-env")
public class AppEnvConstant {

    public static String PRIVATE_VARIABLE;
    private String privateVariable;

    @PostConstruct
    public void init(){
        // 初始化静态常量
        PRIVATE_VARIABLE=privateVariable;
    }
}
