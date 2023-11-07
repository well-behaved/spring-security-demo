package com.xue.security.helloword.config;

import com.xue.security.helloword.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserService myUserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();  //关闭CSRF验证
        http.authorizeRequests().anyRequest().authenticated();  //对所有接口都进行拦截
        http.formLogin()
                .loginPage("/login")             //自定义Login登录页面
                .usernameParameter("myUsername") //自定义username的参数名称
                .passwordParameter("myPassword") //自定义password的参数名称
                .loginProcessingUrl("/doLogin")  //定义的表单提交后的中转地址
                .defaultSuccessUrl("/success")   //验证成功后跳转的地址
                .failureUrl("/failure")          //验证失败跳转的地址
                .permitAll();                    //与表单登录相关的接口不拦截
    }

    /**
     * 通过自定义服务 获取 用户名和密码
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编辑器
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //使用自定义的用户服务 来 获取用户
        auth.userDetailsService(myUserService).passwordEncoder(bCryptPasswordEncoder);
    }

}
