package com.example.javaweb.music_center.service;

import com.example.javaweb.music_center.dao.ProductImageDAO;
import com.example.javaweb.music_center.pojo.OrderItem;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    @Autowired ProductImageDAO productImageDAO;
    @Autowired ProductService productService;

//    两个常量 -- 分别表示单个图片和详情图片
    public static final String type_single = "single";
    public static final String type_detail = "detail";

    public void add(ProductImage bean) {
        productImageDAO.save(bean);

    }
    public void delete(int id) {
        productImageDAO.delete(id);
    }

    public ProductImage get(int id) {
        return productImageDAO.findOne(id);
    }

    public List<ProductImage> listSingleProductImages(Product product) {
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, type_single);
    }
    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, type_detail);
    }

    public void setFirstProductImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product);
        if(!singleImages.isEmpty())
            product.setFirstProductImage(singleImages.get(0));
        else
            //考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。
            product.setFirstProductImage(new ProductImage());

    }
    public void setFirstProductImages(List<Product> products) {
        for (Product product : products)
            setFirstProductImage(product);
    }
    public void setFirstProdutImagesOnOrderItems(List<OrderItem> ois) {
        for (OrderItem orderItem : ois) {
            setFirstProductImage(orderItem.getProduct());
        }
    }

}
