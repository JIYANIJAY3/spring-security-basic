package com.inexture.service;

import com.inexture.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> userList = new ArrayList<>();

    public UserService()
    {
        userList.add(new User("jay","123","jay@gmail.com"));
        userList.add(new User("ritish","12345","ritish@gmail.com"));
    }

    // get all user list
    public List<User> getUserList()
    {
        return userList;
    }

    //get single user

    public User getUser(String username)
    {
        return this.userList.stream().filter((user -> user.getUserName().equals(username))).findAny().orElse(null);
    }

    //add new user
    public User addUser(User user)
    {
        this.userList.add(user);
        return user;
    }
}
