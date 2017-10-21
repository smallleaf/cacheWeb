package com.share1024.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by yesheng on 2017/10/16.
 */
@Controller
public class UserController {


    @RequestMapping("admin")
    public ModelAndView admin(){
        return new ModelAndView("admin");
    }


    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("login");
    }

    @RequestMapping("/login")
    public ModelAndView log2(){
        return new ModelAndView("login");
    }


    @RequestMapping("DoLogin")
    public ModelAndView login(String username,String password){

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            return new ModelAndView("admin");
        }
        try {
            subject.login(token);
            if(subject.isAuthenticated()){
                return new ModelAndView("admin");
            }
        }catch (Exception e){

        }
        return new ModelAndView("login");
    }




}
