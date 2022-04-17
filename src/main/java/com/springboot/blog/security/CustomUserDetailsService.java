package com.springboot.blog.security;

import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: springboot-blog-rest-api
 * @description:
 * @author: Yaowen Hu
 * @create: 2022-04-17 15:39
 **/

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override   //change the parameter, username or email is all fine~
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),   //构造方法返回的是用户名, 密码, 和roles list构造的user detail类
                user.getPassword(), mapRolesToAuthority(user.getRoles()));
    }

    //在数据库重的role需要加上"ROLE_"作为前缀, 否则不能被GrantedAuthority识别
    //数据库密码的存储需要存储encode之后的数据
    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Set<Role> roles) { //私有方法, 用来将roles的set转化为List
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
