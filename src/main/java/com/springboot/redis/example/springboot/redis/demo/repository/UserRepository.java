package com.springboot.redis.example.springboot.redis.demo.repository;

import com.springboot.redis.example.springboot.redis.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by vsinha
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneById(Long id);
}
