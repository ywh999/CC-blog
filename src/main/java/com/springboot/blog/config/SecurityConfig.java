package com.springboot.blog.config;

import com.springboot.blog.security.CustomUserDetailsService;
import com.springboot.blog.security.JwtAuthenticationEntryPoint;
import com.springboot.blog.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: springboot-blog-rest-api
 * @description: security config
 * @author: Yaowen Hu
 * @create: 2022-04-17 11:35
 **/

@Configuration
@EnableWebSecurity
//Spring Security用于启用Web安全的注解。典型的用法是该注解用在某个Web安全配置类上(实现了接口WebSecurityConfigurer或者继承自WebSecurityConfigurerAdapter)
//也就是说我们加上了@EnableWebSecurity这个注解，就是往IOC容器中注入了WebSecurityConfiguration这个类
@EnableGlobalMethodSecurity(prePostEnabled = true)   //提供我们controller方法里的AOP, 可以让控制器里的方法使用注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter authenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
        //对密码进行编码
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   //关闭csrf防护, 避免每次请求要带上csrf token
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)//设置处理异常的入口, Filter的异常会从这里出来
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//设置不会创建或使用任何session,jwt是无状态的, 不用在服务端存信息
                .and()
                .authorizeRequests()  //开启请求的权限配置
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()  //表示所匹配的ant风格的url, 任何用户都可以访问
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest()  //表示匹配所有请求
                .authenticated();  //允许认证过的用户访问
            http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class); //在指定的usernamePasswordFilter之前加上token的filter

        //改为jwtToken验证
//                .and()
//                .httpBasic();  //http基本认证，每次请求时，在请求头Authorization参数中附带用户/密码的base64编码，参考base64
    }

    //Spring Security中对用户身份认证的类,构建auth manager来定义认证规则
    // AuthenticationManagerBuilder（身份验证管理生成器）
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    //在auth controller里创建AuthenticationManager对象
    //AuthenticationManager 这个接口方法非常奇特，入参和返回值的类型都是 Authentication 。
    // 该接口的作用是对用户的未授信凭据进行认证，认证通过则返回授信状态的凭据，否则将抛出认证异常 AuthenticationException
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //    //硬编码创建任务,里面有inmemory的几个用户对象
//    @Override
//    @Bean //向IOC容器注入用户对象
//    protected UserDetailsService userDetailsService() {
//        UserDetails yaowen = User.builder().username("yaowen").password(passwordEncoder()
//                .encode("password")).roles("USER").build();
//        UserDetails admin = User.builder().username("admin").password(passwordEncoder()
//                .encode("admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(yaowen, admin);
//    }
}
