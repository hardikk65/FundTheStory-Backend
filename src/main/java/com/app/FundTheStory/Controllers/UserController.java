package com.app.FundTheStory.Controllers;


import com.app.FundTheStory.Entities.User;
import com.app.FundTheStory.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/add")
    public void insertUser(@RequestBody User user){
        System.out.println("Entered the function");
        userService.insertUser(user);
    }


}
