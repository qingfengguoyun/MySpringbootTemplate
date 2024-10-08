package com.myTemplateProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域访问配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };

    static final String EXPOSED_HEADERS[] = new String[] {"token","Authorization","Content-Disposition"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有的当前站点的请求地址，都支持跨域访问。
                .allowedOrigins("*") // 所有的外部域都可跨域访问。 如果是localhost则很难配置，因为在跨域请求的时候，外部域的解析可能是localhost、127.0.0.1、主机名
                .allowCredentials(true) // 是否支持跨域用户凭证
                .allowedMethods(ORIGINS) // 当前站点支持的跨域请求类型是什么
                .maxAge(3601)// 超时时长设置为1小时。 时间单位是秒。
                //用于前端从header获取指定字段
                //Content-Disposition用于文件下载时存储文件名
                .exposedHeaders(EXPOSED_HEADERS); //设置可被前端访问的header字段,"*"表示暴露所有header字段
    }
}
