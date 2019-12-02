package com.example.javaweb.music_center.service;

import com.example.javaweb.music_center.dao.ProductDAO;
import com.example.javaweb.music_center.pojo.Category;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.example.javaweb.music_center.util.Jieba.splitWords;

@Service
public class ProductService {
    @Autowired ProductDAO productDAO;
    @Autowired CategoryService categoryService;
    @Autowired OrderItemService orderItemService;
    @Autowired ReviewService reviewService;


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

    public Page4Navigator<Product> list(int cid, int start, int size, int navigatePages){
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page<Product> pageFromJPA = productDAO.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public void fill(List<Category> categorys) {
        for (Category category : categorys) {
            fill(category);
        }
    }
    public void fill(Category category) {
        List<Product> products = listByCategory(category);
        // productImageService.setFirstProdutImages(products);
        category.setProducts(products);
    }

    public void fillByRow(List<Category> categorys) {
        int productNumberEachRow = 4;
        for (Category category : categorys) {
            List<Product> products =  category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= Math.min(size, products.size());
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
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
        List<Product> products_ = productDAO.findByNameOrSubTitleLike("%"+keyword+"%","%"+keyword+"%");
        List<String> words = splitWords(keyword);

//        遍历产品库，计算含有多少个关键词
        List<Product> products = productDAO.findAll();
        for (Product product : products){
            int count = 0;
            for (String word : words){
                if (product.getName().contains(word) || product.getSubTitle().contains(word)){
                    count++;
                }
            }
            product.setContains(count);
       }

        products.sort(Product::compareTo);

        for (Product product : products){
//            System.out.println(products_.size() + " " + products_.contains(product));
            if (products_.size() >= 16 || 0 == product.getContains()) break;
            if (!products_.contains(product)){
                products_.add(product);
            }
        }
        return products_;
    }

}
