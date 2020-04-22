package com.favoriteboards.userboards.modelview;

import java.time.ZonedDateTime;

public class BoardFollowView {

    private Long id;
    private ZonedDateTime createdAt;
    private Long board_id;
    private Long user_id;

    public BoardFollowView(Long id, ZonedDateTime createdAt, Long board_id, Long user_id) {
        this.id = id;
        this.createdAt = createdAt;
        this.board_id = board_id;
        this.user_id = user_id;
    }
}
