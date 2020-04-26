package com.favoriteboards.userboards.dto;

import java.time.ZonedDateTime;

public class BoardFollowDTO {

    private Long id;
    private ZonedDateTime createdAt;
    private Long board_id;
    private Long user_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public BoardFollowDTO(Long id, ZonedDateTime createdAt, Long board_id, Long user_id) {
        this.id = id;
        this.createdAt = createdAt;
        this.board_id = board_id;
        this.user_id = user_id;
    }


}
