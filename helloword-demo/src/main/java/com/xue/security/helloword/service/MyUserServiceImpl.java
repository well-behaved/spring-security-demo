package com.xue.security.helloword.service;

import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;

@Service
public class MyUserServiceImpl implements MyUserService {

    /**
     * 实现通过用户姓名 获取 获取用户信息接口
     *
     * @param userName the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        //密码编辑器
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //略过从数据库中获取 随便返回一个
        return new User(
                userName//账号
                , bCryptPasswordEncoder.encode("123456")//密码
                , new ArrayList<>()
        );
    }
}
