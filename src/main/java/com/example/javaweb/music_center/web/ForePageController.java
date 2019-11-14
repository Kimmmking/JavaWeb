package com.example.javaweb.music_center.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ForePageController {
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
    public String logout(HttpSession session) {
        session.removeAttribute("user");
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
}
