package com.sarthak.twitterclone.follow;

import com.sarthak.twitterclone.common.exception.InvalidActionException;
import com.sarthak.twitterclone.common.exception.ResourceNotFoundException;
import com.sarthak.twitterclone.user.User;
import com.sarthak.twitterclone.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService (FollowRepository followRepository, UserRepository userRepository){
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void follow(Long followerId, Long targetUserId){
        if (followerId.equals(targetUserId)) {
            throw new InvalidActionException("Cannot follow yourself");
        }

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("Follower not found"));

        User target = userRepository.findById(targetUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Target user not found"));

        if (followRepository.existsByFollowerAndFollowing(follower, target)) {
            return; // idempotent
        }

        Follow follow = new Follow(follower, target);
        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(Long followerId, Long targetUserId) {

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("Follower not found"));

        User target = userRepository.findById(targetUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Target user not found"));

        followRepository.deleteByFollowerAndFollowing(follower, target);
    }
}
