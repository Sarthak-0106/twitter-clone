package com.sarthak.twitterclone.follow;

import com.sarthak.twitterclone.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByFollowerAndFollowing(User follower, User following);

    void deleteByFollowerAndFollowing(User follower, User following);

    Page<Follow> findByFollower(User user, Pageable pageable);

    Page<Follow> findByFollowing(User user, Pageable pageable);
}
