package com.favoriteboards.userboards.repository;

import com.favoriteboards.userboards.model.Board;
import com.favoriteboards.userboards.model.BoardFollow;
import com.favoriteboards.userboards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardFollowRepository extends JpaRepository <BoardFollow, Long > {
    public List <BoardFollow> findAll();
    public BoardFollow findByid(Long id);
    public Long countByid(Long id);

    @Query("SELECT r.board FROM BoardFollow r " +
            "where r.user.id = :user_id")
    public List<Board> findByUserId(Long user_id );


}

