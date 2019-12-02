package com.example.javaweb.music_center.web;

import com.example.javaweb.music_center.pojo.Category;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.service.CategoryService;
import com.example.javaweb.music_center.service.OrderItemService;
import com.example.javaweb.music_center.service.ProductService;
import com.example.javaweb.music_center.util.Page4Navigator;
import com.example.javaweb.music_center.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "10") int size) throws Exception {
        start = start < 0 ? 0 : start;
        Page4Navigator<Category> page = categoryService.list(start, size, 6);  //10表示导航分页最多有10个，像 [1,2,3,4,5,6,...] 这样
        return page;
    }

    @PostMapping("/categories")
    public Object add(Category bean, HttpServletRequest request) throws Exception {
        categoryService.add(bean);
        // saveOrUpdateImageFile(bean, image, request);
        return bean;
    }

//    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
//            throws IOException {
//        File imageFolder= new File(request.getServletContext().getRealPath("img/category"));
//        File file = new File(imageFolder,bean.getId()+".jpg");
//        if(!file.getParentFile().exists())
//            file.getParentFile().mkdirs();
//        image.transferTo(file);
//        BufferedImage img = ImageUtil.change2jpg(file);
//        ImageIO.write(img, "jpg", file);
//    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
        categoryService.delete(id);
//        File  imageFolder= new File(request.getServletContext().getRealPath("img/category"));
//        File file = new File(imageFolder,id+".jpg");
//        file.delete();
        return null;
    }

    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") int id) throws Exception {
        return categoryService.get(id);
    }

    @PostMapping("/categories/{id}")
    public Object update(Category bean, HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        bean.setName(name);
        categoryService.update(bean);

//        if(image!=null) {
//            saveOrUpdateImageFile(bean, image, request);
//        }
        return bean;
    }

    @GetMapping("/categories/saleinfo")
    public Object get4chart(){
        List<Category> categories = categoryService.list();
        List<Integer> saleCount = new ArrayList<>();

        List<String> dateName = new ArrayList<>();
        List<Float> dailySale = new ArrayList<>();

        for(Category category : categories){
            int count = 0;
            List<Product> products = productService.listByCategory(category);
            for(Product product : products){
                count += orderItemService.getSaleCount(product);
            }
            saleCount.add(count);
        }

        Map<String,Object> map= new HashMap<>();
        map.put("categories", categories);
        map.put("saleCount", saleCount);

        return Result.success(map);

    }
}
