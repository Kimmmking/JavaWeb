package com.example.javaweb.music_center.web;

import com.example.javaweb.music_center.dao.History3DAO;
import com.example.javaweb.music_center.pojo.History3;
import com.example.javaweb.music_center.pojo.History4;
import com.example.javaweb.music_center.pojo.Salesman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminPageController {
    @Autowired
    History3DAO history3DAO;

    @GetMapping(value="/admin")
    public String admin(){
        return "redirect:admin_login";
    }

    @GetMapping(value = "/admin_login")
    public String adminLogin(){
        return "admin/loginPage";
    }

    @GetMapping(value="/admin_category_list")
    public String listCategory(){
        return "admin/listCategory";
    }

    @GetMapping(value="/admin_category_edit")
    public String editCategory(){
        return "admin/editCategory";
    }

    @GetMapping(value="/admin_order_list")
    public String listOrder(){
        return "admin/listOrder";
    }

    @GetMapping(value="/admin_product_list")
    public String listProduct(){
        return "admin/listProduct";
    }

    @GetMapping(value="/admin_product_edit")
    public String editProduct(){
        return "admin/editProduct";
    }

    @GetMapping(value="/admin_productImage_list")
    public String listProductImage(){
        return "admin/listProductImage";
    }

    @GetMapping(value="/admin_property_list")
    public String listProperty(){
        return "admin/listProperty";
    }

    @GetMapping(value="/admin_property_edit")
    public String editProperty(){
        return "admin/editProperty";
    }

    @GetMapping(value="/admin_propertyValue_edit")
    public String editPropertyValue(){
        return "admin/editPropertyValue";
    }

    @GetMapping(value="/admin_user_list")
    public String listUser(){
        return "admin/listUser";
    }

    @GetMapping(value="/admin_salesman_list")
    public String listSalesman(){
        return "admin/listSalesman";
    }

    @GetMapping(value="/admin_salesman_edit")
    public String editSalesman(){
        return "admin/editSalesman";
    }

    @GetMapping(value = "/admin_chart_show")
    public String showChart(){
        return "admin/chartShow";
    }
}
