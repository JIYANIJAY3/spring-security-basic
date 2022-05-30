package com.inexture.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("custom: "+username);
        if(username.equals("jay@gmail.com"))
        {
            return new User("jay@gmail.com","123",new ArrayList<>());
        }
        else
        {
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
