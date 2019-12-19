package com.example.javaweb.music_center.web;

import com.example.javaweb.music_center.pojo.Category;
import com.example.javaweb.music_center.pojo.OrderItem;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.service.CategoryService;
import com.example.javaweb.music_center.service.OrderItemService;
import com.example.javaweb.music_center.service.ProductService;
import com.example.javaweb.music_center.util.ImageUtil;
import com.example.javaweb.music_center.util.Page4Navigator;
import com.example.javaweb.music_center.util.Result;
import org.aspectj.weaver.patterns.TypePatternQuestions;
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
        return bean;
    }

//    private void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
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
    public String delete(@PathVariable("id") int id)  throws Exception {
        categoryService.delete(id);
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
        return bean;
    }

    @GetMapping("/categories/saleinfo")
    public Object get4chart(){

        List<Category> categories = categoryService.list();
        List<Integer> saleCount = new ArrayList<>();


        List<OrderItem> orderItems = orderItemService.getAll();
        float[] saleAmount = new float[4];
        int num = 0;
        for(Category category : categories){
            saleAmount[num++] = 0;
            int count = 0;
            List<Product> products = productService.listByCategory(category);
            for(Product product : products){
                count += orderItemService.getSaleCount(product);
            }
            saleCount.add(count);
        }


        for(OrderItem orderItem : orderItems){
            if(orderItem.getOrder() == null || "delete".equals(orderItem.getOrder().getStatus()) || "waitPay".equals(orderItem.getOrder().getStatus())){
            }else{
                Product product = orderItem.getProduct();
                float price = product.getPromotePrice();
                int count = orderItem.getNumber();
                switch (product.getCategory().getName()){
                    case "内地榜":
                        saleAmount[0] += (price * count);
                        break;
                    case "香港榜":
                        saleAmount[1] += (price * count);
                        break;
                    case "台湾榜":
                        saleAmount[2] += (price * count);
                        break;
                    case "欧美榜":
                        saleAmount[3] += (price * count);
                        break;
                }
            }
        }

        List<Float> saleInfo = new ArrayList<>();

        for(int i=0; i<num;){
            saleInfo.add(saleAmount[i++]);
        }

        Map<String,Object> map= new HashMap<>();
        map.put("categories", categories);
        map.put("saleCount", saleCount);
        map.put("saleInfo", saleInfo);

        return Result.success(map);

    }
}
