package com.sarthak.twitterclone.follow;

import com.sarthak.twitterclone.user.User;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
        name = "follows",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"follower_id", "following_id"})
        }
)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "following_id")
    private User following;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected Follow() {}

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    public User getFollower() {
        return follower;
    }

    public User getFollowing() {
        return following;
    }
}

