package com.xue.demo.security.controllers;

import com.xue.demo.security.dto.ClassDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String hello() {
        ClassDO demoDo = new ClassDO();
        demoDo.setClassId(new Random().nextInt(1000) + "");
        demoDo.setClassName("班级名和曾呢");
        return demoDo.getClassName();
    }
}
