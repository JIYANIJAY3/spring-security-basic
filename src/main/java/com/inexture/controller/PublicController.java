package com.inexture.controller;

import com.inexture.model.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PublicController {

    @Autowired
    private JwtController jwtController;

    @GetMapping("/signin")
    public String home()
    {
        return "index";
    }

    @PostMapping("/welcome")
    public String welcome() throws Exception {

        ResponseEntity<?> token =  jwtController.createAuthenticationToken(new JwtRequest("jay@gmail.com","123"));

        System.out.println(token);


        //get token in stirng
       // session, model("token","ghnrt");
        return "/log";
    }

    @RequestMapping("/log2")
    public String log2()
    {
        return "log2";
    }
}
