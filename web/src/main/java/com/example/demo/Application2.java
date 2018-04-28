package com.example.demo;

import com.example.demo.common.interceptor.SecurityInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2016/10/13.
 */
@SpringBootApplication
@EnableAutoConfiguration
//@ConfigurationProperties
//@EnableTransactionManagement
public class Application2 extends SpringBootServletInitializer  {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application2.class, args);
//        DubboConsumerServiceImpl dubboConsumerService = run.getBean(DubboConsumerServiceImpl.class);
//        dubboConsumerService.getName("success");
    }

    //    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(this.getClass());
//    }

    @Configuration
    static class WebMvcConfigurer extends WebMvcConfigurerAdapter {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/*Manager/*.do").addPathPatterns("/*Manager/*.json");
//            registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/*.do").addPathPatterns("/*.json");
//        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/user/**");
//            super.addInterceptors(registry);
        }
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
            registry.addResourceHandler("/qiniuu/**").addResourceLocations("classpath:/qiniuu/");
//                registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
//                super.addResourceHandlers(registry);
        }
    }
}
