package com.example.javaweb.music_center.service;

import com.example.javaweb.music_center.dao.SalesmanDAO;
import com.example.javaweb.music_center.pojo.Salesman;
import com.example.javaweb.music_center.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SalesmanService {
    @Autowired
    SalesmanDAO salesmanDAO;

    public boolean isExist(String name){
        Salesman salesman = salesmanDAO.findByName(name);
        return null != salesman;
    }

    public Salesman getByNameAndPassword(String name, String password){
        if(salesmanDAO.findByNameAndPassword(name,password) != null){
            return salesmanDAO.findByNameAndPassword(name,password);
        }
        else return null;
    }
    public Salesman get(int id) {
        return salesmanDAO.getOne(id);
    }

    public void delete(int id) {
        salesmanDAO.deleteById(id);
    }
    public void update(Salesman bean) {
        salesmanDAO.save(bean);
    }

    public Page4Navigator<Salesman> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = salesmanDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public void add(Salesman salesman) {
        salesmanDAO.save(salesman);
    }
}
