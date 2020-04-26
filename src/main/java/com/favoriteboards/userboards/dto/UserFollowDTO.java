package com.favoriteboards.userboards.dto;

import com.favoriteboards.userboards.model.User;

import javax.persistence.*;

public class UserFollowDTO {

    private Long id;
    private Long userFollowing_id;
    private Long userFollower_id;

    public UserFollowDTO(Long id, Long userFollowing_id, Long userFollower_id) {
        this.id = id;
        this.userFollowing_id = userFollowing_id;
        this.userFollower_id = userFollower_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserFollowing_id() {
        return userFollowing_id;
    }

    public void setUserFollowing_id(Long userFollowing_id) {
        this.userFollowing_id = userFollowing_id;
    }

    public Long getUserFollower_id() {
        return userFollower_id;
    }

    public void setUserFollower_id(Long userFollower_id) {
        this.userFollower_id = userFollower_id;
    }
}
