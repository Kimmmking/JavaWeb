package com.example.javaweb.music_center.interceptor;

import com.example.javaweb.music_center.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o){
        HttpSession session = httpServletRequest.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "adminchart",
                "categories",
                "products",
                "properties",
                "propertyValues",
                "orders",
                "productImages",
                "users"
        };
        String uri = httpServletRequest.getRequestURI();
        uri = StringUtils.remove(uri,contextPath+"/");
        String page = uri;

        if(beginWith(page,requireAuthPages)){
            User user = (User) session.getAttribute("admin");
            if(user == null){
                try {
                    httpServletResponse.sendRedirect("adminlogin");
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private boolean beginWith(String page, String[] requireAuthPages){
        boolean result = false;
        for (String requiredAuthPage : requireAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
