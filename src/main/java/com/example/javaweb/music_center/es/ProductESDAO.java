package com.example.javaweb.music_center.es;

import com.example.javaweb.music_center.pojo.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductESDAO extends ElasticsearchRepository<Product, Integer> {
}
