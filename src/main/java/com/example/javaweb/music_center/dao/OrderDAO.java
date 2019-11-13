package com.example.javaweb.music_center.dao;

import com.example.javaweb.music_center.pojo.Order;
import com.example.javaweb.music_center.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    public List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);
}
