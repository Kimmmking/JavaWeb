package com.example.javaweb.music_center.dao;

import com.example.javaweb.music_center.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findByName(String name);
    User findByEmail(String email);
    User getByNameAndPassword(String name, String password);

}
