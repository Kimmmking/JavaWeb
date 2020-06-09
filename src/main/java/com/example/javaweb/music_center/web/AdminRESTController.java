package com.example.javaweb.music_center.web;

import com.example.javaweb.music_center.dao.History3DAO;
import com.example.javaweb.music_center.pojo.*;
import com.example.javaweb.music_center.service.SalesmanService;
import com.example.javaweb.music_center.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class AdminRESTController {
    @Autowired
    SalesmanService salesmanService;
    @Autowired
    History3DAO history3DAO;

    @PostMapping("/adminregister")
    public Object register(@RequestBody Salesman salesman){
        String name = salesman.getName();
        name = HtmlUtils.htmlEscape(name);

        boolean exist = salesmanService.isExist(name);

        if (exist){
            String message = "该名字已被注册";
            return Result.fail(message);
        }
        salesman.setName(name);
        salesmanService.add(salesman);
        return Result.success();
    }

    @PostMapping("/adminlogin")
    public Object login(@RequestBody Salesman salesmanParam, HttpSession session, HttpServletRequest request){
        Salesman salesman = salesmanService.getByNameAndPassword(salesmanParam.getName(), salesmanParam.getPassword());
        System.out.println(salesmanParam.getName()+" "+salesmanParam.getPassword());
        if(null == salesman){
            String message ="账号密码错误";
            return Result.fail(message);
        }
        else{
            session.setAttribute("salesman", salesman);
            String ip = request.getRemoteAddr();
            History3 history3 = new History3();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(new Date());
            history3.setTime(time);
            history3.setSalesman(salesman);
            history3.setOperate("login");
            history3.setIp(ip);
            history3DAO.save(history3);
            return Result.success();
        }

    }

    @GetMapping("/adminlogout")
    public String logout(HttpSession session, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        History3 history3 = new History3();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        history3.setTime(time);
        history3.setSalesman((Salesman) session.getAttribute("salesman"));
        history3.setOperate("logout");
        history3.setIp(ip);
        history3DAO.save(history3);
        session.removeAttribute("salesman");
        return "redirect:admin_login";
    }

    @GetMapping("/admincheckLogin")
    public Object checkLogin( HttpSession session) {
        Salesman salesman =(Salesman)  session.getAttribute("salesman");
        if(null != salesman)
            return Result.success();
        return Result.fail("未登录");
    }
}
