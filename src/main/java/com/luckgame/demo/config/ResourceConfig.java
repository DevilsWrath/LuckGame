package com.luckgame.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourcePath = "file:///C:/Users/DELL/Desktop/demo/src/main/resources/static";
        registry.addResourceHandler("/")
                .addResourceLocations(resourcePath);
    }
}
