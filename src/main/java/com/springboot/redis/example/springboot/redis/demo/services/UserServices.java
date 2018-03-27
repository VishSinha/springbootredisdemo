package com.springboot.redis.example.springboot.redis.demo.services;

import com.springboot.redis.example.springboot.redis.demo.model.User;
import com.springboot.redis.example.springboot.redis.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public User findUserById(Long id) {
       return userRepository.findOneById(id);
    }


    public void deleteUserById(Long id) {
        User user = userRepository.findOneById(id);
        if(user !=null) {
            userRepository.delete(user);
        }
    }

    public void updateUser(User user) {
       userRepository.save(user);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
