package com.favoriteboards.userboards.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NamedQueries({@NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
@NoArgsConstructor
@AllArgsConstructor

public class User implements Serializable {
    public static final String FIND_ALL = "user.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference(value="user_board")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference(value="user-boardsf")
    private List<BoardFollow> boards_following = new ArrayList<>();

    @OneToMany(mappedBy = "userFollowing", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference(value="user-userfing")
    private List<UserFollow> userFollowing = new ArrayList<>();

    @OneToMany(mappedBy = "userFollower", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference(value="user-userfer")
    private List<UserFollow> userFollower = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public List<BoardFollow> getBoards_following() {
        return boards_following;
    }

    public void setBoards_following(List<BoardFollow> boards_following) {
        this.boards_following = boards_following;
    }

    public List<UserFollow> getUserFollowing() {
        return userFollowing;
    }

    public void setUserFollowing(List<UserFollow> userFollowing) {
        this.userFollowing = userFollowing;
    }

    public List<UserFollow> getUserFollower() {
        return userFollower;
    }

    public void setUserFollower(List<UserFollow> userFollower) {
        this.userFollower = userFollower;
    }
}
