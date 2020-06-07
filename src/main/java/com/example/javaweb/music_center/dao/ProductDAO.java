package com.example.javaweb.music_center.dao;

import com.example.javaweb.music_center.pojo.Category;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.pojo.Salesman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    Page<Product> findBySalesmanAndCategory(Salesman salesman, Category category, Pageable pageable);
    Page<Product> findByCategory(Category category, Pageable pageable);
	List<Product> findByCategoryOrderById(Category category);
    List<Product> findByNameContaining(String keyword);
    List<Product> findBySubTitleContaining(String keyword);
}
