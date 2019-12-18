package com.ketai.edu.config;

import com.ketai.edu.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 拦截器
     * addPathPatterns：用于设置拦截器的过滤路径规则；
     * addPathPatterns("/**")对所有请求都拦截
     * excludePathPatterns：用于设置不需要拦截的过滤规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器 放掉某些特定不需要校验token的路由
        registry.addInterceptor(new JwtInterceptor())
                .excludePathPatterns("/admin/edu/user/login")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .excludePathPatterns("/admin/edu/course/**")
                .excludePathPatterns("/admin/edu/chapter/**")
                .excludePathPatterns("/admin/edu/subject/import");

    }

    /**
     * 配置静态访问资源
     * addResoureHandler：指的是对外暴露的访问路径
     * addResourceLocations：指的是内部文件放置的目录
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
