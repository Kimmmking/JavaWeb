package com.example.javaweb.music_center.dao;

import com.example.javaweb.music_center.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
