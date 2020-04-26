package com.favoriteboards.userboards.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.favoriteboards.userboards.model.BoardFollow;
import com.favoriteboards.userboards.model.User;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardDTO {

    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private Long user_id;

    public BoardDTO(Long id, String name, String description, String createdAt, Long user_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
