package com.example.javaweb.music_center.service;

import com.example.javaweb.music_center.dao.ProductDAO;
import com.example.javaweb.music_center.es.ProductESDAO;
import com.example.javaweb.music_center.pojo.Category;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.pojo.Review;
import com.example.javaweb.music_center.util.Page4Navigator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired ProductDAO productDAO;
    @Autowired CategoryService categoryService;
    @Autowired OrderItemService orderItemService;
    @Autowired ReviewService reviewService;

    @Autowired
    ProductESDAO productESDAO;

    public void add(Product bean) {
        productDAO.save(bean);
        productESDAO.save(bean);
    }

    public void delete(int id) {
        productDAO.delete(id);
        productESDAO.delete(id);
    }

    public Product get(int id) {
        return productDAO.findOne(id);
    }

    public void update(Product bean) {
        productDAO.save(bean);
        productESDAO.save(bean);
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
                size= size>products.size()?products.size():size;
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

//    public List<Product> search(String keyword, int start, int size) {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(start, size, sort);
//        return productDAO.findByNameLike("%"+keyword+"%",pageable);
//    }
    
    public List<Product> search(String keyword, int start, int size) {
        initDatabase2ES();
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchQuery("name", keyword),
                        ScoreFunctionBuilders.weightFactorFunction(100))
                .add(QueryBuilders.matchQuery("subTitle", keyword),
                        ScoreFunctionBuilders.weightFactorFunction(50))
                .scoreMode("sum")
                .setMinScore(10);
        Sort sort  = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start, size,sort);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
        Page<Product> page = productESDAO.search(searchQuery);
        return page.getContent();
    }

    private void initDatabase2ES() {
        Pageable pageable = new PageRequest(0, 5);
        Page<Product> page =productESDAO.findAll(pageable);
        if(page.getContent().isEmpty()) {
            List<Product> products= productDAO.findAll();
            for (Product product : products) {
                productESDAO.save(product);
            }
        }
    }
}
