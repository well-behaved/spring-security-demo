package com.xue.security.helloword.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 通过配置累的方式设置 登录用户名 密码 登录成功和失败跳转页面等
 */
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
        //登出
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html")
                .permitAll();
        //记住我
        http.rememberMe();
    }

    /**
     * 通过配置类自定义账号密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置用户名和密码
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("admin");
    }
}
