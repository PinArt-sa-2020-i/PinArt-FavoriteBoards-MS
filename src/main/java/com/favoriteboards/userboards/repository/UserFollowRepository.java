package com.favoriteboards.userboards.repository;

import com.favoriteboards.userboards.model.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowRepository extends JpaRepository <UserFollow, Long> {

    public List<UserFollow> findAll();
    public UserFollow findByid(Long id);
    public Long countByid(Long id);
}





