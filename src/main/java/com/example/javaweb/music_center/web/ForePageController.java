package com.example.javaweb.music_center.web;

import com.example.javaweb.music_center.dao.History1DAO;
import com.example.javaweb.music_center.pojo.History1;
import com.example.javaweb.music_center.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ForePageController {
    @Autowired
    History1DAO history1DAO;

    @GetMapping(value = "/")
    public String index(){
        return "redirect:home";
    }

    @GetMapping(value = "/home")
    public String home(){
        return "fore/home";
    }

    @GetMapping(value = "/register")
    public String register(){
        return "fore/register";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "fore/login";
    }

    @GetMapping("/forelogout")
    public String logout(HttpSession session, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            subject.logout();
        String ip = request.getRemoteAddr();
        History1 history1 = new History1();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        history1.setTime(time);
        history1.setUser((User) session.getAttribute("user"));
        history1.setOperate("logout");
        history1.setIp(ip);
        history1DAO.save(history1);
        return "redirect:home";
    }

    @GetMapping(value="/product")
    public String product(){
        return "fore/product";
    }

    @GetMapping(value="/category")
    public String category(){
        return "fore/category";
    }

    @GetMapping(value="/buy")
    public String buy(){
        return "fore/buy";
    }

    @GetMapping(value="/cart")
    public String cart(){
        return "fore/cart";
    }

    @GetMapping(value="/pay")
    public String pay(){
        return "fore/pay";
    }

    @GetMapping(value = "/search")
    public String search(){
        return "fore/search";
    }

    @GetMapping(value = "/bought")
    public String bought(){
        return "fore/bought";
    }

    @GetMapping(value = "/payed")
    public String payed(){
        return "fore/payed";
    }
}
