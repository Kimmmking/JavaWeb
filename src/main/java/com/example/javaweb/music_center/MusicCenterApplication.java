package com.example.javaweb.music_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class MusicCenterApplication{
    public static void main(String[] args) {
        SpringApplication.run(MusicCenterApplication.class, args);
    }

}

