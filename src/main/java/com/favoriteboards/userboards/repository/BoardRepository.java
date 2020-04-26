package com.favoriteboards.userboards.repository;

import com.favoriteboards.userboards.model.Board;
import com.favoriteboards.userboards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository <Board, Long> {
    public List<Board> findAll();
    public Board findByid(Long id);
    public Long countByid(Long id);

    @Query("SELECT r FROM Board r where r.user.id = :user_id")
    public List<Board> findByUserId(Long user_id );

    @Query("SELECT r FROM Board r where r.name = :name")
    public List<Board> findByName(String name );



}



