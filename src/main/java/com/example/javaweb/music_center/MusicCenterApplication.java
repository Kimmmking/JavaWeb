package com.example.javaweb.music_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.javaweb.music_center.util.PortUtil;
@SpringBootApplication
//@EnableCaching
//@EnableElasticsearchRepositories(basePackages = "com.example.javaweb.music_center.es")
//@EnableJpaRepositories(basePackages = {"com.example.javaweb.music_center.dao", "com.example.javaweb.music_center.pojo"})
public class MusicCenterApplication {
//    static {
//        PortUtil.checkPort(6379,"Redis 服务端",true);
//        PortUtil.checkPort(9300,"ElasticSearch 服务端",true);
//        PortUtil.checkPort(5601,"Kibana 工具", true);
//    }
    public static void main(String[] args) {
        SpringApplication.run(MusicCenterApplication.class, args);
    }
}

