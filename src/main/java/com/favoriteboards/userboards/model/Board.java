package com.favoriteboards.userboards.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "board")
@NamedQueries({@NamedQuery(name = Board.FIND_ALL, query = "SELECT u FROM Board u")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
@NoArgsConstructor
@AllArgsConstructor

public class Board implements Serializable {
    public static final String FIND_ALL = "board.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    private String description;

    @NonNull
    private ZonedDateTime createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = ZonedDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonBackReference(value="user_board")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference(value="user-boardsf")
    private List<BoardFollow> boards_following = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public ZonedDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt (ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BoardFollow> getBoards_following() {
        return boards_following;
    }

    public void setBoards_following(List<BoardFollow> boards_following) {
        this.boards_following = boards_following;
    }
}
