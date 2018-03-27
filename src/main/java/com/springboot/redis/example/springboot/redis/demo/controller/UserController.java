package com.springboot.redis.example.springboot.redis.demo.controller;

import com.springboot.redis.example.springboot.redis.demo.model.User;
import com.springboot.redis.example.springboot.redis.demo.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by vsinha
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames={"users"})
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServices userServices;

    /**
     * Retrieve User by Id
     * @param userId
     * @return
     */
    @Cacheable(key = "#userId")
    @GetMapping(value = "/user/{userId}")
    public User getUser(@PathVariable String userId) {
        LOGGER.info("Retrieve user with Id {}.", userId);
        return userServices.findUserById(Long.valueOf(userId));
    }

    /**
     * Update User
     * @param user
     * @return
     */
    @CachePut(key = "#user.id")
    @PostMapping("/updateUser")
    public User updatePerson(@RequestBody User user) {
        userServices.updateUser(user);
        return user;
    }

    /**
     * Delete User
     * @param userId
     */
    @CacheEvict(key = "#userId")
    @DeleteMapping("/user/{userId}")
    public void deleteUserById(@PathVariable String userId) {
        LOGGER.info("Delete user with id {}", userId);
        userServices.deleteUserById(Long.valueOf(userId));
    }
}
