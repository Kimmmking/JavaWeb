package com.example.javaweb.music_center.dao;

import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewDAO extends JpaRepository<Review, Integer> {

	List<Review> findByProductOrderByIdDesc(Product product);
	int countByProduct(Product product);

}
