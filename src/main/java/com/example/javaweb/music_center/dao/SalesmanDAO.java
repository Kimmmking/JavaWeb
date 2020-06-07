package com.example.javaweb.music_center.dao;

import com.example.javaweb.music_center.pojo.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesmanDAO extends JpaRepository<Salesman, Integer> {
    Salesman findByNameAndPassword(String name, String password);
    Salesman findByName(String name);
}
