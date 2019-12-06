package com.example.javaweb.music_center.service;


import com.example.javaweb.music_center.dao.CategoryDAO;
import com.example.javaweb.music_center.pojo.Category;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.util.Page4Navigator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired CategoryDAO categoryDAO;

    public void add(Category bean) {
        categoryDAO.save(bean);
    }

    public void delete(int id) {
        categoryDAO.deleteById(id);
    }

    public Category get(int id) {
        return categoryDAO.getOne(id);
    }

    public void update(Category bean) {
        categoryDAO.save(bean);
    }

    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Category> pageFromJPA =categoryDAO.findAll(pageable);

        return new Page4Navigator<Category>(pageFromJPA,navigatePages);
    }

//    @Cacheable(key="'categories-all'")
    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return categoryDAO.findAll(sort);
    }


    public void removeCategoryFromProduct(List<Category> cs) {
        for (Category category : cs) {
            removeCategoryFromProduct(category);
        }
    }

    public
    void removeCategoryFromProduct(Category category) {
        List<Product> products = category.getProducts();
        if(null != products) {
            for (Product product : products) {
                product.setCategory(null);
            }
        }

    }
}