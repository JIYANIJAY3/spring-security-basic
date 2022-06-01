package com.inexture.controller;

import com.inexture.model.User;
import com.inexture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class HomeController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_Admin')")
    @GetMapping("/getalluser")
    public List<User> getAllUser()
    {
        return this.userService.getUserList();
    }

    @PreAuthorize("hasRole('ROLE_User')")
    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String username)
    {
        System.out.println("username"+username);
        return this.userService.getUser(username);
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user)
    {
        return this.userService.addUser(user);
    }

}
