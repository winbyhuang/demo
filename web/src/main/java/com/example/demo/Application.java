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
 * @author  winby
 * @date 2016/10/13.
 */
//@ConfigurationProperties
//@EnableTransactionManagement
@SpringBootApplication
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer  {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
//        FlywayUtil flywayUtil = run.getBean(FlywayUtil.class);
//        flywayUtil.migrate();
//        flywayUtil.clean();
//        Properties properties = System.getProperties();
//        System.out.println(11);
//        DemoServiceImpl demoService = run.getBean(DemoServiceImpl.class);
//        demoService.save(new DemoDO());
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
