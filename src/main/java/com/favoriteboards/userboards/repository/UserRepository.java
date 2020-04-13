package com.favoriteboards.userboards.repository;

import com.favoriteboards.userboards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findAll();
    public User findByid(Long id);
    public Long countByid(Long id);
}
