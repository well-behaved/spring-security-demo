package com.xue.security.helloword.controllers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class TestController {

    @RequestMapping(value = "/success")
    public ModelAndView success(){
        String username = null;
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  //取出身份信息
        if (object instanceof UserDetails){
            UserDetails userDetails = (UserDetails) object;
            username = userDetails.getUsername();
        }else {
            Principal principal = (Principal) object;
            username = principal.getName();
        }
        return new ModelAndView("/success", "username", username);
    }

    @RequestMapping(value = "/failure")
    public ModelAndView failure(HttpServletRequest request){
        AuthenticationException authenticationException = (AuthenticationException)
                request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);  //取出错误信息
        return new ModelAndView("/failure", "error", authenticationException.getLocalizedMessage());
    }

    @RequestMapping(value = "/login")
    public String  login(){
        return "login";
    }
}
