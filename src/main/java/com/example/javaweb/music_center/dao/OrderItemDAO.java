package com.example.javaweb.music_center.dao;

import com.example.javaweb.music_center.pojo.Order;
import com.example.javaweb.music_center.pojo.OrderItem;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {
	List<OrderItem> findByOrderOrderByIdDesc(Order order);
	List<OrderItem> findByProduct(Product product);
	List<OrderItem> findByUserAndOrderIsNull(User user);
}
