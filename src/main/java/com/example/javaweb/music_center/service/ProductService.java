package com.example.javaweb.music_center.service;

import com.example.javaweb.music_center.dao.ProductDAO;
import com.example.javaweb.music_center.pojo.Category;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.pojo.Salesman;
import com.example.javaweb.music_center.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static com.example.javaweb.music_center.util.Jieba.splitWords;

@Service
public class ProductService {
    @Autowired ProductDAO productDAO;
    @Autowired CategoryService categoryService;
    @Autowired OrderItemService orderItemService;
    @Autowired ReviewService reviewService;
    @Autowired ProductImageService productImageService;


    public void add(Product bean) {
        productDAO.save(bean);
    }

    public void delete(int id) {
        productDAO.deleteById(id);
    }

    public Product get(int id) {
        return productDAO.getOne(id);
    }

    public void update(Product bean) {
        productDAO.save(bean);
    }

    public Page4Navigator<Product> list(int cid, int start, int size, int navigatePages, HttpSession session){
        Salesman salesman = (Salesman) session.getAttribute("salesman");
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page<Product> pageFromJPA;
        if(salesman.getId() != 1){
            pageFromJPA = productDAO.findBySalesmanAndCategory(salesman,category,pageable);
        }else {
            pageFromJPA = productDAO.findByCategory(category,pageable);
        }

        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public void fill(List<Category> categorys) {
        for (Category category : categorys) {
            fill(category);
        }
    }
    public void fill(Category category) {
        List<Product> products = listByCategory(category);
        productImageService.setFirstProductImages(products);
        category.setProducts(products);
    }

    public List<Product> listByCategory(Category category){
        return productDAO.findByCategoryOrderById(category);
    }

    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.getSaleCount(product);
        product.setSaleCount(saleCount);

        int reviewCount = reviewService.getCount(product);
        product.setReviewCount(reviewCount);

    }

    public void setSaleAndReviewNumber(List<Product> products) {
        for (Product product : products)
            setSaleAndReviewNumber(product);
    }

    public List<Product> search(String keyword) throws IOException {

        if(null == keyword){
            return new ArrayList<>();
        }

        List<Product> products = new ArrayList<>();
        products.addAll(productDAO.findByNameContaining(keyword));
        products.addAll(productDAO.findBySubTitleContaining(keyword));
        return products;
    }

}
