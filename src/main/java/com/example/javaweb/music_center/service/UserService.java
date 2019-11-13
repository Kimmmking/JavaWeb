package com.example.javaweb.music_center.service;

import com.example.javaweb.music_center.dao.UserDAO;
import com.example.javaweb.music_center.pojo.User;
import com.example.javaweb.music_center.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String email){
        User user = userDAO.findByEmail(email);
        return null != user;
    }

    public User getByEmail(String email){
        return userDAO.findByEmail(email);
    }

    public User get(String email, String password){
        if(userDAO.findByEmail(email) != null){
            User user = userDAO.findByEmail(email);
            if (user.getPassword().equals(password)){
                return user;
            }else
                return null;
        }
        else return null;
    }

    public Page4Navigator<User> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public void add(User user) {
        userDAO.save(user);
    }

}
