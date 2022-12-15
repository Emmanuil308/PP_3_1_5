package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("user_home_page");
    }

//    @Bean(name = "commonsMultipartResolver")
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }
//
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//
//        factory.setMaxFileSize(DataSize.parse("10MB"));
//        factory.setMaxRequestSize(DataSize.parse("10MB"));
//
//        return factory.createMultipartConfig();
//    }
}
