package com.example.javaweb.music_center.web;

import com.example.javaweb.music_center.pojo.MailVo;
import com.example.javaweb.music_center.pojo.User;
import com.example.javaweb.music_center.service.MailService;
import com.example.javaweb.music_center.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class MainController {
    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

//    /**
//     * 发送邮件的主界面
//     */
//    @GetMapping("/mail")
//    public ModelAndView index() {
//        ModelAndView mv = new ModelAndView("/fore/email");//打开发送邮件的页面
//        mv.addObject("from", "434407818@qq.com");//邮件发信人
//        return mv;
//    }

//    /**
//     * 发送邮件
//     */
//    @PostMapping("/mail/send")
//    public MailVo sendMail(MailVo mailVo, MultipartFile[] files) {
//        mailVo.setMultipartFiles(files);
//        return mailService.sendMail(mailVo);//发送邮件和附件
//    }

    /**
     * 发送邮件
     */
    @GetMapping("/mail/send")
    public MailVo sendMail(HttpSession session) {
        MailVo mailVo = new MailVo();
        User user = (User) session.getAttribute("user");
        mailVo.setFrom("434407818@qq.com");
        mailVo.setTo(user.getEmail());
        mailVo.setSubject("JavaWeb音乐商城");
        mailVo.setText("感谢您的消费~欢迎再次光临本店(*^_^*)");
        return mailService.sendMail(mailVo);//发送邮件
    }
}
