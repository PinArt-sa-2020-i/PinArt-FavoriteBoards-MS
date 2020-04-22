package com.favoriteboards.userboards.service;

import com.favoriteboards.userboards.model.BoardFollow;
import com.favoriteboards.userboards.model.User;
import com.favoriteboards.userboards.model.UserFollow;
import com.favoriteboards.userboards.repository.BoardFollowRepository;
import com.favoriteboards.userboards.repository.UserFollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFollowService {
    @Autowired
    private UserFollowRepository userFollowRepository;

    public List<UserFollow> findAll(){
        return userFollowRepository.findAll();
    }

    public List<User> findFollowingsByFollower(Long follower_id){
        return userFollowRepository.findFollowingByFollowerId(follower_id);
    }

    public UserFollow getUserFollow(Long id){
        return userFollowRepository.findByid(id);
    }

    public UserFollow getUserFollow(Long follower_id, Long following_id){
        return userFollowRepository.findUserFollow(follower_id,following_id).get(0);

    }


    public Long countUserFollow(Long id) {
        return userFollowRepository.countByid(id);
    }

    public UserFollow createUserFollow(UserFollow userFollow){
        return userFollowRepository.save(userFollow);
    }

    public void deleteUserFollow(UserFollow userFollow) {
        userFollowRepository.delete(userFollow);
    }

}

