package com.example.javaweb.music_center.dao;

import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.pojo.Property;
import com.example.javaweb.music_center.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDAO extends JpaRepository<PropertyValue, Integer> {

	List<PropertyValue> findByProductOrderByIdDesc(Product product);
	PropertyValue getByPropertyAndProduct(Property property, Product product);

	
	
	
}

