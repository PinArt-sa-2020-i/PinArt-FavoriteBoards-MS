package com.favoriteboards.userboards.repository;

import com.favoriteboards.userboards.model.Board;
import com.favoriteboards.userboards.model.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserFollowRepository extends JpaRepository <UserFollow, Long> {

    public List<UserFollow> findAll();


    @Query("SELECT r.userFollowing.id FROM UserFollow r where r.userFollower.id = :follower_id")
    List<Long> findFollowingByFollowerId(@Param("follower_id") Long follower_id);
    //public List<UserFollow> findAllBy
    public UserFollow findByid(Long id);
    public Long countByid(Long id);

    @Query("SELECT r FROM UserFollow r where (r.userFollower.id = :fer_id) and (r.userFollowing.id=:fing_id)")
    List<UserFollow> findUserFollow(@Param("fer_id") Long fer_id, @Param("fing_id") Long fing_id );



}





