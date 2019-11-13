package com.example.javaweb.music_center.dao;

import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {
	public List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);
	
}
