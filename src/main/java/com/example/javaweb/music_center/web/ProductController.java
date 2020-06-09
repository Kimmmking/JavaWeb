package com.example.javaweb.music_center.web;

import com.example.javaweb.music_center.dao.History4DAO;
import com.example.javaweb.music_center.pojo.History4;
import com.example.javaweb.music_center.pojo.Product;
import com.example.javaweb.music_center.pojo.Salesman;
import com.example.javaweb.music_center.service.CategoryService;
import com.example.javaweb.music_center.service.ProductService;
import com.example.javaweb.music_center.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    History4DAO history4DAO;

    private String[] operation = {
            "add",
            "remove",
            "update"
    };

    @GetMapping("/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "10") int size, HttpSession session) throws Exception {
        start = start < 0 ? 0 : start;
        Page4Navigator<Product> page = productService.list(cid, start, size,10, session);

        return page;
    }

    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id") int id) throws Exception {
        return productService.get(id);
    }

    @PostMapping("/products")
    public Object add(@RequestBody Product bean, HttpSession session, HttpServletRequest request) throws Exception {
        bean.setCreateDate(new Date());
        String ip = request.getRemoteAddr();
        Salesman salesman = (Salesman)session.getAttribute("salesman");
        bean.setSalesman(salesman);
        History4 history4 = new History4();
        history4.setOperate(operation[0]);
        history4.setSalesman(salesman);
        history4.setPname(bean.getName());
        history4.setIp(ip);
        history4DAO.save(history4);
        productService.add(bean);
        return bean;
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id") int id, HttpSession session, HttpServletRequest request)  throws Exception {
        String ip = request.getRemoteAddr();
        History4 history4 = new History4();
        history4.setOperate(operation[1]);
        history4.setSalesman((Salesman)session.getAttribute("salesman"));
        history4.setPname(productService.get(id).getName());
        history4.setIp(ip);
        history4DAO.save(history4);
        productService.delete(id);
        return null;
    }

    @PutMapping("/products")
    public Object update(@RequestBody Product bean, HttpSession session, HttpServletRequest request) throws Exception {
        productService.update(bean);
        String ip = request.getRemoteAddr();
        History4 history4 = new History4();
        history4.setOperate(operation[2]);
        history4.setSalesman((Salesman)session.getAttribute("salesman"));
        history4.setPname(bean.getName());
        history4.setIp(ip);
        history4DAO.save(history4);
        return bean;
    }
}
