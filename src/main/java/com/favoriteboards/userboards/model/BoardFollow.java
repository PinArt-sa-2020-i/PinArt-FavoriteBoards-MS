package com.favoriteboards.userboards.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "board_follow")
@NamedQueries({@NamedQuery(name = BoardFollow.FIND_ALL, query = "SELECT u FROM BoardFollow u")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
@NoArgsConstructor
@AllArgsConstructor

public class BoardFollow {
    public static final String FIND_ALL = "board_follow.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private ZonedDateTime createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = ZonedDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonBackReference(value="user-boardsf")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonBackReference(value="board-boardsf")
    private Board board;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt (ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
