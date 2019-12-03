package com.example.javaweb.music_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootApplication
public class MusicCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicCenterApplication.class, args);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        File path = null;
//        try {
//            path = new File(ResourceUtils.getURL("classpath:").getPath());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        assert path != null;
//        String gitPath=path.getParentFile().getParentFile().getParent()+File.separator+"javaweb"+File.separator+"uploads"+File.separator;
//        registry.addResourceHandler("/uploads/**").addResourceLocations(gitPath);
//        registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
//        super.addResourceHandlers(registry);
//    }

}

