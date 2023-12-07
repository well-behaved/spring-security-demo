package com.xue.security.helloword.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xue.security.helloword.dto.ClassDO;

import java.util.Random;

/**
 * @author: xuexiong@souche.com
 * @date: 2021/9/2 20:00
 * @description:
 */
@RestController("DemoController")
@RequestMapping("/demo")
public class DemoController {

    /**
     * 添加一个
     *
     * @return
     */
    @GetMapping("/hello")
    //拥有 sale权限的才可以请求这个方法
    @Secured({"ROULE_sale"})
    public ClassDO hello() {
        ClassDO demoDo = new ClassDO();
        demoDo.setClassId(new Random().nextInt(1000) + "");
        demoDo.setClassName("班级名和曾呢");
        return demoDo;
    }
}
