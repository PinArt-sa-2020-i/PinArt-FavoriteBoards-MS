package com.favoriteboards.userboards.repository;

import com.favoriteboards.userboards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findAll();
   // public User findByid(Long id);


    @Query("select u from User u where u.id = :id")
    public User findUser(Long id);

    User findByid(Long id);
    public Long countByid(Long id);
}
