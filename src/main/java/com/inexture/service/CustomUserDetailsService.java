package com.inexture.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("custom: "+username);
        if(username.equals("jay@gmail.com"))
        {
            return new User("jay@gmail.com","123",getUserAuthority());
        }
        else if(username.equals("admin@gmail.com"))
        {
            return new User("admin@gmail.com","123",getAdminAuthority());
        }
        else
        {
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    private Set<SimpleGrantedAuthority> getUserAuthority()
    {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "User"));
        return authorities;
    }

    private Set<SimpleGrantedAuthority> getAdminAuthority()
    {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "Admin"));
        return authorities;
    }
}
