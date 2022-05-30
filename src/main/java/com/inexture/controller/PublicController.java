package com.inexture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    @GetMapping("/signin")
    public String home()
    {
        return "index";
    }
}
