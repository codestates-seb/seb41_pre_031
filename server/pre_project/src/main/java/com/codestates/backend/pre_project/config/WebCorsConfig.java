package com.codestates.backend.pre_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowCredentials(true)
                .allowedOriginPatterns("https://8a8d-211-51-37-45.jp.ngrok.io")
                        .allowedOriginPatterns("http://localhost:4002")
                                .allowedHeaders("*")
                                .allowedMethods("*");
    }
}