package com.inexture.controller;

import com.inexture.model.JwtRequest;
import com.inexture.model.JwtResponse;
import com.inexture.service.CustomUserDetailsService;
import com.inexture.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/gentoken",method = RequestMethod.POST)
    public void createAuthenticationToken (@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }
        catch (UsernameNotFoundException | BadCredentialsException e)
        {
            e.printStackTrace();
            throw  new Exception("Bad Credential");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);

        System.out.println("JWT"+token);

        ResponseEntity.ok(new JwtResponse(token));
    }
}
