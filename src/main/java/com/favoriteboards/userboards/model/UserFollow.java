package com.favoriteboards.userboards.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "user_follow")
@NamedQueries({@NamedQuery(name = UserFollow.FIND_ALL, query = "SELECT u FROM UserFollow u")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
@NoArgsConstructor
@AllArgsConstructor

public class UserFollow {
    public static final String FIND_ALL = "user_follow.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonBackReference(value="user-userfing")
    private User userFollowing;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonBackReference(value="user-userfer")
    private User userFollower;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserFollowing() {
        return userFollowing;
    }

    public void setUserFollowing(User userFollowing) {
        this.userFollowing = userFollowing;
    }

    public User getUserFollower() {
        return userFollower;
    }

    public void setUserFollower(User userFollower) {
        this.userFollower = userFollower;
    }
}
