package com.example.javaweb.music_center.web;

import com.example.javaweb.music_center.pojo.User;
import com.example.javaweb.music_center.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminRESTController {
    @PostMapping("/adminlogin")
    public Object login(@RequestBody User userParam, HttpSession session){
        System.out.println("adminlogin");
        String email =  userParam.getEmail();
        String password = userParam.getPassword();
        if("10086".equals(password) && "root".equals(email)){
            User user = new User();
            user.setEmail("root");
            user.setPassword("10086");
            session.setAttribute("admin", user);
            return Result.success();
        }else{
            String message ="账号密码错误";
            return Result.fail(message);
        }
    }

    @GetMapping("/admincheckLogin")
    public Object checkLogin( HttpSession session) {
        User user =(User)  session.getAttribute("admin");
        if(null!=user)
            return Result.success();
        return Result.fail("未登录");
    }
}
