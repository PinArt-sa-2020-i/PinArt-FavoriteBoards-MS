package com.favoriteboards.userboards.repository;

import com.favoriteboards.userboards.model.BoardFollow;
import com.favoriteboards.userboards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardFollowRepository extends JpaRepository <BoardFollow, Long > {
    public List <BoardFollow> findAll();
    public BoardFollow findByid(Long id);
    public Long countByid(Long id);
}

