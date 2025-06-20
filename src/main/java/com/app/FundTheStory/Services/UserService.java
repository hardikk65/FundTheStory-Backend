package com.app.FundTheStory.Services;


import com.app.FundTheStory.Entities.User;
import com.app.FundTheStory.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void insertUser(User user){
        userRepository.save(user);
        System.out.println("User Add Successfully");
    }
}
